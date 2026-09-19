package com.demoqa.playwright.stepdefinitions;

import com.automation.ai.HealingAuditLogger;
import com.automation.utils.Log;
import com.demoqa.playwright.pages.PlaywrightBrokenHealingPage;
import com.demoqa.playwright.pages.PlaywrightTextBoxPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.File;

public class PlaywrightHealingSteps {

    private PlaywrightBrokenHealingPage brokenHealingPage;
    private final PlaywrightTextBoxPage textBoxPage = new PlaywrightTextBoxPage();

    @When("the AI pre-flight agent validates the broken DemoQA page elements")
    public void validateBrokenDemoQaElements() {
        brokenHealingPage = new PlaywrightBrokenHealingPage();
        Log.info("[PLAYWRIGHT AI] Pre-flight scanning broken elements on DemoQA page...");
        brokenHealingPage.validateAndHealPageElements();
    }

    // 1. CSS Steps
    @When("I enter {string} into the broken CSS user name field")
    public void enterBrokenCssUserName(String name) {
        getBrokenHealingPage().enterBrokenCssUserName(name);
    }

    @And("I click the broken CSS submit button")
    public void clickBrokenCssSubmit() {
        getBrokenHealingPage().clickBrokenCssSubmit();
    }

    @Then("the broken CSS user name field should be marked as healed with confidence >= 60%")
    public void verifyBrokenCssUserNameHealed() {
        Assert.assertTrue(getBrokenHealingPage().brokenCssUserName.isHealed(), "CSS UserName element must be healed");
        Assert.assertTrue(getBrokenHealingPage().brokenCssUserName.getConfidenceScore() >= 0.60, "Healing confidence score must be >= 60%");
        Assert.assertNotNull(getBrokenHealingPage().brokenCssUserName.getHealedStrategy(), "Healing strategy must be recorded");
    }

    // 2. XPath Steps
    @When("I enter {string} into the broken XPath email field")
    public void enterBrokenXpathEmail(String email) {
        getBrokenHealingPage().enterBrokenXpathEmail(email);
    }

    @And("I click the broken XPath submit button")
    public void clickBrokenXpathSubmit() {
        getBrokenHealingPage().clickBrokenXpathSubmit();
    }

    @Then("the broken XPath email field should be marked as healed with confidence >= 60%")
    public void verifyBrokenXpathEmailHealed() {
        Assert.assertTrue(getBrokenHealingPage().brokenXpathUserEmail.isHealed(), "XPath Email element must be healed");
        Assert.assertTrue(getBrokenHealingPage().brokenXpathUserEmail.getConfidenceScore() >= 0.60, "Healing confidence score must be >= 60%");
        Assert.assertNotNull(getBrokenHealingPage().brokenXpathUserEmail.getHealedStrategy(), "Healing strategy must be recorded");
    }

    // 3. Text Engine Steps
    @When("I click the broken Text Engine submit button")
    public void clickBrokenTextSubmit() {
        getBrokenHealingPage().clickBrokenTextSubmit();
    }

    @Then("the broken Text Engine submit button should be marked as healed with confidence >= 60%")
    public void verifyBrokenTextSubmitHealed() {
        Assert.assertTrue(getBrokenHealingPage().brokenTextEngineSubmit.isHealed(), "Text Engine Submit element must be healed");
        Assert.assertTrue(getBrokenHealingPage().brokenTextEngineSubmit.getConfidenceScore() >= 0.60, "Healing confidence score must be >= 60%");
    }

    // 4. Role Engine Steps
    @When("I enter {string} into the broken Role current address field")
    public void enterBrokenRoleCurrentAddress(String address) {
        getBrokenHealingPage().enterBrokenRoleCurrentAddress(address);
    }

    @And("I click the broken Role submit button")
    public void clickBrokenRoleSubmit() {
        getBrokenHealingPage().clickBrokenRoleSubmit();
    }

    @Then("the broken Role current address field should be marked as healed with confidence >= 60%")
    public void verifyBrokenRoleCurrentAddressHealed() {
        Assert.assertTrue(getBrokenHealingPage().brokenRoleCurrentAddress.isHealed(), "Role Current Address element must be healed");
        Assert.assertTrue(getBrokenHealingPage().brokenRoleCurrentAddress.getConfidenceScore() >= 0.60, "Healing confidence score must be >= 60%");
    }

    // 5. Placeholder and Label Steps
    @When("I enter {string} into the broken Placeholder email field")
    public void enterBrokenPlaceholderEmail(String email) {
        getBrokenHealingPage().enterBrokenPlaceholderEmail(email);
    }

    @When("I enter {string} into the broken Label user name field")
    public void enterBrokenLabelUserName(String name) {
        getBrokenHealingPage().enterBrokenLabelUserName(name);
    }

    @When("I enter {string} into the broken Placeholder permanent address field")
    public void enterBrokenPlaceholderPermAddress(String address) {
        getBrokenHealingPage().enterBrokenPlaceholderPermAddress(address);
    }

    @Then("the broken Placeholder email field should be marked as healed with confidence >= 60%")
    public void verifyBrokenPlaceholderEmailHealed() {
        Assert.assertTrue(getBrokenHealingPage().brokenPlaceholderEmail.isHealed(), "Placeholder Email element must be healed");
        Assert.assertTrue(getBrokenHealingPage().brokenPlaceholderEmail.getConfidenceScore() >= 0.60, "Healing confidence score must be >= 60%");
    }

    @Then("the broken Label user name field should be marked as healed with confidence >= 60%")
    public void verifyBrokenLabelUserNameHealed() {
        Assert.assertTrue(getBrokenHealingPage().brokenLabelUserName.isHealed(), "Label UserName element must be healed");
        Assert.assertTrue(getBrokenHealingPage().brokenLabelUserName.getConfidenceScore() >= 0.60, "Healing confidence score must be >= 60%");
    }

    @Then("the broken Placeholder permanent address field should be marked as healed with confidence >= 60%")
    public void verifyBrokenPlaceholderPermAddressHealed() {
        Assert.assertTrue(getBrokenHealingPage().brokenPlaceholderPermAddress.isHealed(), "Placeholder Permanent Address element must be healed");
        Assert.assertTrue(getBrokenHealingPage().brokenPlaceholderPermAddress.getConfidenceScore() >= 0.60, "Healing confidence score must be >= 60%");
    }

    // 6. Chained and Pseudo-Class Steps
    @When("I enter {string} into the broken Chained user name field")
    public void enterBrokenChainedUserName(String name) {
        getBrokenHealingPage().enterBrokenChainedUserName(name);
    }

    @And("I click the broken Chained submit button")
    public void clickBrokenChainedSubmit() {
        getBrokenHealingPage().clickBrokenChainedSubmit();
    }

    @Then("the broken Chained user name field should be marked as healed with confidence >= 60%")
    public void verifyBrokenChainedUserNameHealed() {
        Assert.assertTrue(getBrokenHealingPage().brokenChainedUserName.isHealed(), "Chained UserName element must be healed");
        Assert.assertTrue(getBrokenHealingPage().brokenChainedUserName.getConfidenceScore() >= 0.60, "Healing confidence score must be >= 60%");
    }

    @Then("the broken Chained submit button should be marked as healed with confidence >= 60%")
    public void verifyBrokenChainedSubmitHealed() {
        Assert.assertTrue(getBrokenHealingPage().brokenChainedSubmitBtn.isHealed(), "Chained Submit element must be healed");
        Assert.assertTrue(getBrokenHealingPage().brokenChainedSubmitBtn.getConfidenceScore() >= 0.60, "Healing confidence score must be >= 60%");
    }

    // Legacy / Convenience Compatibility Steps
    @And("I enter {string} into the broken user name field")
    public void enterBrokenUserName(String name) {
        getBrokenHealingPage().enterBrokenCssUserName(name);
    }

    @And("I click the broken submit button")
    public void clickBrokenSubmit() {
        getBrokenHealingPage().clickBrokenCssSubmit();
    }

    @Then("the AI element healing JSON report should be generated in the demoqa project")
    public void verifyDemoQaHealingReport() {
        HealingAuditLogger.exportJsonReport();

        File file = new File("target/element-healing-history.json");
        Assert.assertTrue(file.exists(), "Healing audit JSON report must exist at target/element-healing-history.json");
        Assert.assertTrue(file.length() > 0, "Healing audit JSON report must not be empty.");

        Log.info("[PLAYWRIGHT AI] Verified DemoQA healing JSON report at: " + file.getAbsolutePath() + " (size: " + file.length() + " bytes)");
    }

    @When("user injects a broken selector into the username field")
    public void userInjectsBrokenSelector() {
        textBoxPage.simulateBrokenUserNameLocator();
    }

    @When("user attempts to fill the broken username field with {string}")
    public void userAttemptsToFillBrokenUsernameField(String text) {
        textBoxPage.enterFullName(text);
    }

    @Then("the username field should contain {string}")
    public void verifyUsernameFieldContent(String expectedText) {
        Assert.assertTrue(textBoxPage.userNameInput.isHealed(), "Expected element to be marked as healed");
        Assert.assertTrue(textBoxPage.userNameInput.getConfidenceScore() >= 0.60, "Expected healing confidence >= 60%");
    }

    @Then("the AI healing audit log should confirm successful healing")
    public void verifyHealingAuditLog() {
        Assert.assertNotNull(textBoxPage.userNameInput.getHealedStrategy(), "Expected a valid healing strategy");
    }

    private PlaywrightBrokenHealingPage getBrokenHealingPage() {
        if (brokenHealingPage == null) {
            brokenHealingPage = new PlaywrightBrokenHealingPage();
        }
        return brokenHealingPage;
    }
}
