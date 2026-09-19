package com.demoqa.playwright.stepdefinitions;

import com.automation.config.ConfigReader;
import com.automation.playwright.PlaywrightManager;
import com.automation.utils.Log;
import com.demoqa.playwright.pages.PlaywrightSelectMenuPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

public class PlaywrightSelectMenuSteps {

    private PlaywrightSelectMenuPage selectMenuPage;

    @Given("I open the DemoQA Select Menu page")
    public void openSelectMenuPage() {
        String url = ConfigReader.get("app.selectmenu.url");
        Log.info("[PLAYWRIGHT] Navigating to Select Menu page: " + url);
        PlaywrightManager.navigate(url);
        selectMenuPage = new PlaywrightSelectMenuPage();
        selectMenuPage.validateAndHealPageElements();
    }

    @When("I select option {string} from the Select Value group dropdown")
    public void selectGroupOption(String option) {
        selectMenuPage.selectGroupOption(option);
    }

    @Then("the selected group option should be {string}")
    public void verifyGroupOption(String expected) {
        String actual = selectMenuPage.getSelectedGroupOptionText();
        Log.info("[PLAYWRIGHT] Verified group option: [" + actual + "]");
        Assert.assertEquals(actual, expected);
    }

    @When("I select title {string} from the Select One dropdown")
    public void selectTitleOne(String title) {
        selectMenuPage.selectTitleOne(title);
    }

    @Then("the selected title should be {string}")
    public void verifyTitleOne(String expected) {
        String actual = selectMenuPage.getSelectedTitleOneText();
        Log.info("[PLAYWRIGHT] Verified title one: [" + actual + "]");
        Assert.assertEquals(actual, expected);
    }

    @When("I select color {string} from the Old Style Select Menu")
    public void selectOldStyleColor(String color) {
        selectMenuPage.selectOldStyleOption(color);
    }

    @Then("the selected old style color should be {string}")
    public void verifyOldStyleColor(String expected) {
        String actual = selectMenuPage.getSelectedOldStyleOption();
        Log.info("[PLAYWRIGHT] Verified old style color: [" + actual + "]");
        Assert.assertEquals(actual, expected);
    }

    @When("I select multiple colors {string} from the React multiselect dropdown")
    public void selectMultipleReactColors(String colorsCsv) {
        List<String> colors = Arrays.stream(colorsCsv.split(",")).map(String::trim).toList();
        selectMenuPage.selectMultiColorOptions(colors);
    }

    @Then("the selected React multiselect badges should contain {string}")
    public void verifyMultipleReactBadges(String colorsCsv) {
        List<String> expected = Arrays.stream(colorsCsv.split(",")).map(String::trim).toList();
        List<String> actual = selectMenuPage.getSelectedMultiColorOptions();
        Log.info("[PLAYWRIGHT] Verified multi-select badges: " + actual);
        for (String exp : expected) {
            Assert.assertTrue(actual.contains(exp), "Missing selected badge: " + exp);
        }
    }

    @When("I select cars {string} from the Standard multi-select")
    public void selectStandardCars(String carsCsv) {
        List<String> cars = Arrays.stream(carsCsv.split(",")).map(String::trim).toList();
        selectMenuPage.selectStandardCars(cars);
    }

    @Then("the selected cars should contain {string}")
    public void verifySelectedCars(String carsCsv) {
        List<String> expected = Arrays.stream(carsCsv.split(",")).map(String::trim).toList();
        List<String> actual = selectMenuPage.getSelectedStandardCars();
        Log.info("[PLAYWRIGHT] Verified selected standard cars: " + actual);
        for (String exp : expected) {
            Assert.assertTrue(actual.contains(exp), "Missing selected car: " + exp);
        }
    }
}
