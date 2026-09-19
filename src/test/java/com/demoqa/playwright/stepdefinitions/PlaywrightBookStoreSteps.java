package com.demoqa.playwright.stepdefinitions;

import com.automation.config.ConfigReader;
import com.automation.playwright.PlaywrightManager;
import com.automation.utils.Log;
import com.demoqa.playwright.pages.PlaywrightBookStorePage;
import com.demoqa.playwright.pages.PlaywrightLoginPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.File;
import java.util.List;
import java.util.Map;

public class PlaywrightBookStoreSteps {

    private PlaywrightBookStorePage bookStorePage;
    private PlaywrightLoginPage loginPage;
    private final ObjectMapper mapper = new ObjectMapper();

    // --- BOOK STORE ---
    @Given("I open the DemoQA Book Store page")
    public void openBookStorePage() {
        String url = ConfigReader.get("app.books.url");
        Log.info("[PLAYWRIGHT] Navigating to Book Store page: " + url);
        PlaywrightManager.navigate(url);
        bookStorePage = new PlaywrightBookStorePage();
    }

    @When("I search for books with query {string}")
    public void searchBooks(String query) {
        bookStorePage.searchBook(query);
    }

    @Then("the book search results should contain {string}")
    public void verifyBookResults(String expectedTitle) {
        List<String> titles = bookStorePage.getBookTitles();
        Log.info("[PLAYWRIGHT] Found book titles: " + titles);
        Assert.assertTrue(titles.stream().anyMatch(t -> t.contains(expectedTitle)),
                "Expected book not found: " + expectedTitle);
    }

    @When("I click the book title {string}")
    public void clickBookTitle(String title) {
        bookStorePage.clickBookByTitle(title);
    }

    @Then("the book {string} field should be {string}")
    public void verifyBookDetail(String fieldName, String expectedValue) {
        String actual = bookStorePage.getBookDetailValue(fieldName);
        Log.info("[PLAYWRIGHT] Book detail [" + fieldName + "]: " + actual);
        Assert.assertEquals(actual, expectedValue);
    }

    @When("I click Back To Book Store button")
    public void clickBackToStore() {
        bookStorePage.clickBackToBookStore();
    }

    // --- LOGIN ---
    @Given("I open the DemoQA Login page")
    public void openLoginPage() {
        String url = ConfigReader.get("app.login.url");
        Log.info("[PLAYWRIGHT] Navigating to Login page: " + url);
        PlaywrightManager.navigate(url);
        loginPage = new PlaywrightLoginPage();
    }

    @When("I attempt login using profile {string} from JSON")
    public void attemptLogin(String profileKey) throws Exception {
        Map<String, Map<String, Object>> data = mapper.readValue(
                new File("src/test/resources/data/user_form_data.json"),
                new TypeReference<Map<String, Map<String, Object>>>() {}
        );
        Map<String, Object> credentials = data.get(profileKey);

        loginPage.login((String) credentials.get("username"), (String) credentials.get("password"));
    }

    @Then("the login error message should display {string}")
    public void verifyLoginError(String expectedMsg) {
        String actual = loginPage.getErrorMessageText();
        Log.info("[PLAYWRIGHT] Login error message: " + actual);
        Assert.assertEquals(actual, expectedMsg);
    }
}
