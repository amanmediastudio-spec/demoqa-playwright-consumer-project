package com.demoqa.playwright.stepdefinitions;

import com.automation.config.ConfigReader;
import com.automation.playwright.PlaywrightManager;
import com.automation.utils.Log;
import com.demoqa.playwright.pages.PlaywrightPracticeFormPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.File;
import java.util.List;
import java.util.Map;

public class PlaywrightFormsSteps {

    private PlaywrightPracticeFormPage practiceFormPage;
    private final ObjectMapper mapper = new ObjectMapper();

    @Given("I open the DemoQA Automation Practice Form page")
    public void openPracticeFormPage() {
        String url = ConfigReader.get("app.practiceform.url");
        Log.info("[PLAYWRIGHT] Navigating to Practice Form page: " + url);
        PlaywrightManager.navigate(url);
        practiceFormPage = new PlaywrightPracticeFormPage();
    }

    @When("I fill and submit the student registration form using profile {string} from JSON")
    public void fillAndSubmitStudentForm(String profileKey) throws Exception {
        Map<String, Map<String, Object>> data = mapper.readValue(
                new File("src/test/resources/data/user_form_data.json"),
                new TypeReference<Map<String, Map<String, Object>>>() {}
        );
        Map<String, Object> profile = data.get(profileKey);

        practiceFormPage.fillPersonalDetails(
                (String) profile.get("firstName"),
                (String) profile.get("lastName"),
                (String) profile.get("email"),
                (String) profile.get("gender"),
                (String) profile.get("mobileNumber")
        );

        practiceFormPage.setDateOfBirth(
                (String) profile.get("day"),
                (String) profile.get("month"),
                (String) profile.get("year")
        );

        if (profile.get("subjects") instanceof List<?> subjectsList) {
            List<String> subjects = subjectsList.stream().map(String::valueOf).toList();
            practiceFormPage.addSubjects(subjects);
        }

        if (profile.get("hobbies") instanceof List<?> hobbiesList) {
            List<String> hobbies = hobbiesList.stream().map(String::valueOf).toList();
            practiceFormPage.selectHobbies(hobbies);
        }

        practiceFormPage.uploadPicture("src/test/resources/data/user_form_data.json");

        practiceFormPage.fillAddressAndStateCity(
                (String) profile.get("currentAddress"),
                (String) profile.get("state"),
                (String) profile.get("city")
        );

        practiceFormPage.submitForm();
    }

    @Then("the student registration submission modal title should be {string}")
    public void verifyModalTitle(String expectedTitle) {
        String actual = practiceFormPage.getModalTitle();
        Log.info("[PLAYWRIGHT] Practice form modal title: " + actual);
        Assert.assertEquals(actual, expectedTitle);
    }

    @Then("the student submission summary table should contain {string}")
    public void verifyModalTableSummary(String expectedData) {
        String tableText = practiceFormPage.getModalTableData();
        Log.info("[PLAYWRIGHT] Practice form submission table: \n" + tableText);
        Assert.assertTrue(tableText.contains(expectedData), "Modal table does not contain: " + expectedData);
    }

    @When("I close the student registration modal")
    public void closeModal() {
        practiceFormPage.closeModal();
    }
}
