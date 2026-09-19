package com.demoqa.playwright.stepdefinitions;

import com.automation.config.ConfigReader;
import com.automation.playwright.PlaywrightManager;
import com.automation.utils.Log;
import com.demoqa.playwright.pages.PlaywrightAlertsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class PlaywrightAlertsSteps {

    private PlaywrightAlertsPage alertsPage;

    @Given("I open the DemoQA Alerts page")
    public void openAlertsPage() {
        String url = ConfigReader.get("app.alerts.url");
        Log.info("[PLAYWRIGHT] Navigating to Alerts page: " + url);
        PlaywrightManager.navigate(url);
        alertsPage = new PlaywrightAlertsPage();
    }

    @When("I trigger and accept the simple alert")
    public void triggerSimpleAlert() {
        alertsPage.triggerSimpleAlert();
    }

    @When("I trigger the confirm alert and choose {string}")
    public void triggerConfirmAlert(String choice) {
        alertsPage.triggerConfirmAlert();
    }

    @Then("the confirm selection result should be {string}")
    public void verifyConfirmResult(String expected) {
        String actual = alertsPage.getConfirmResultText();
        Log.info("[PLAYWRIGHT] Verified confirm alert result: " + actual);
        Assert.assertEquals(actual, expected);
    }

    @When("I trigger the prompt alert, enter {string} and accept")
    public void triggerPromptAlert(String text) {
        alertsPage.triggerPromptAlert(text);
    }

    @Then("the prompt response result should display {string}")
    public void verifyPromptResult(String expected) {
        String actual = alertsPage.getPromptResultText();
        Log.info("[PLAYWRIGHT] Verified prompt alert result: " + actual);
        Assert.assertTrue(actual.contains(expected), "Prompt result does not contain: " + expected);
    }
}
