package com.demoqa.playwright.stepdefinitions;

import com.automation.config.ConfigReader;
import com.automation.playwright.PlaywrightManager;
import com.automation.utils.Log;
import com.demoqa.playwright.pages.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

public class PlaywrightWidgetsSteps {

    private PlaywrightAccordianPage accordianPage;
    private PlaywrightAutoCompletePage autoCompletePage;
    private PlaywrightDatePickerPage datePickerPage;
    private PlaywrightSliderPage sliderPage;
    private PlaywrightProgressBarPage progressBarPage;
    private PlaywrightTabsPage tabsPage;
    private PlaywrightToolTipsPage toolTipsPage;
    private PlaywrightMenuPage menuPage;

    @Given("I open the DemoQA Accordian page")
    public void openAccordianPage() {
        String url = ConfigReader.get("app.accordian.url");
        Log.info("[PLAYWRIGHT] Navigating to Accordian page: " + url);
        PlaywrightManager.navigate(url);
        accordianPage = new PlaywrightAccordianPage();
    }

    @When("I click the accordian section heading {int}")
    public void clickAccordianHeading(int section) {
        accordianPage.clickSectionHeading(section);
    }

    @Then("the accordian section {int} content should be displayed")
    public void verifySectionDisplayed(int section) {
        Assert.assertTrue(accordianPage.isSectionContentDisplayed(section), "Section " + section + " is not displayed");
    }

    @Then("the accordian section {int} content should contain {string}")
    public void verifySectionContent(int section, String expectedSnippet) {
        String text = accordianPage.getSectionContentText(section);
        Assert.assertTrue(text.contains(expectedSnippet), "Section " + section + " missing: " + expectedSnippet);
    }

    // --- AUTO COMPLETE ---
    @Given("I open the DemoQA Auto Complete page")
    public void openAutoCompletePage() {
        String url = ConfigReader.get("app.autocomplete.url");
        Log.info("[PLAYWRIGHT] Navigating to Auto Complete page: " + url);
        PlaywrightManager.navigate(url);
        autoCompletePage = new PlaywrightAutoCompletePage();
    }

    @When("I add multiple colors {string} into autocomplete")
    public void addMultipleColors(String colorsCommaSeparated) {
        List<String> colors = Arrays.stream(colorsCommaSeparated.split(","))
                .map(String::trim)
                .toList();
        autoCompletePage.addMultipleColors(colors);
    }

    @Then("the selected multiple color badges should contain {string}")
    public void verifyMultipleColorBadges(String expectedColors) {
        List<String> selected = autoCompletePage.getSelectedMultipleColors();
        Log.info("[PLAYWRIGHT] Selected color badges: " + selected);
        for (String c : expectedColors.split(",")) {
            Assert.assertTrue(selected.contains(c.trim()), "Badge missing color: " + c.trim());
        }
    }

    @When("I remove the color badge {string}")
    public void removeColorBadge(String color) {
        autoCompletePage.removeMultipleColorBadge(color);
    }

    @When("I select single color {string} into autocomplete")
    public void selectSingleColor(String color) {
        autoCompletePage.selectSingleColor(color);
    }

    @Then("the selected single color should be {string}")
    public void verifySingleColor(String expectedColor) {
        String actual = autoCompletePage.getSelectedSingleColor();
        Assert.assertEquals(actual, expectedColor);
    }

    // --- DATE PICKER ---
    @Given("I open the DemoQA Date Picker page")
    public void openDatePickerPage() {
        String url = ConfigReader.get("app.datepicker.url");
        Log.info("[PLAYWRIGHT] Navigating to Date Picker page: " + url);
        PlaywrightManager.navigate(url);
        datePickerPage = new PlaywrightDatePickerPage();
    }

    @When("I set the Select Date to {string}")
    public void setSelectDate(String dateVal) {
        datePickerPage.setSelectDate(dateVal);
    }

    @Then("the Select Date input value should be {string}")
    public void verifySelectDateValue(String expectedVal) {
        Assert.assertEquals(datePickerPage.getSelectedDate(), expectedVal);
    }

    @When("I set the Date and Time to {string}")
    public void setDateAndTime(String dateTimeVal) {
        datePickerPage.setDateTime(dateTimeVal);
    }

    @Then("the Date and Time input value should be {string}")
    public void verifyDateAndTimeValue(String expectedVal) {
        Assert.assertEquals(datePickerPage.getSelectedDateTime(), expectedVal);
    }

    // --- SLIDER ---
    @Given("I open the DemoQA Slider page")
    public void openSliderPage() {
        String url = ConfigReader.get("app.slider.url");
        Log.info("[PLAYWRIGHT] Navigating to Slider page: " + url);
        PlaywrightManager.navigate(url);
        sliderPage = new PlaywrightSliderPage();
    }

    @When("I set the slider value to {int}")
    public void setSliderValue(int val) {
        sliderPage.setSliderValue(val);
    }

    @Then("the slider display value box should be {string}")
    public void verifySliderValue(String expected) {
        Assert.assertEquals(String.valueOf(sliderPage.getSliderValue()), expected);
    }

    // --- PROGRESS BAR ---
    @Given("I open the DemoQA Progress Bar page")
    public void openProgressBarPage() {
        String url = ConfigReader.get("app.progressbar.url");
        Log.info("[PLAYWRIGHT] Navigating to Progress Bar page: " + url);
        PlaywrightManager.navigate(url);
        progressBarPage = new PlaywrightProgressBarPage();
    }

    @When("I start the progress bar and wait for {int}% completion")
    public void startAndCompleteProgressBar(int percent) {
        progressBarPage.startProgressBar();
        progressBarPage.waitForCompletion(25);
        Assert.assertEquals(progressBarPage.getProgressBarValue(), percent);
    }

    @When("I reset the progress bar")
    public void resetProgressBar() {
        progressBarPage.resetProgressBar();
    }

    // --- TABS ---
    @Given("I open the DemoQA Tabs page")
    public void openTabsPage() {
        String url = ConfigReader.get("app.tabs.url");
        Log.info("[PLAYWRIGHT] Navigating to Tabs page: " + url);
        PlaywrightManager.navigate(url);
        tabsPage = new PlaywrightTabsPage();
    }

    @When("I select tab {string}")
    public void selectTab(String tabName) {
        tabsPage.clickTab(tabName);
    }

    @Then("the active tab pane should contain {string}")
    public void verifyTabContent(String expectedSnippet) {
        String content = tabsPage.getActiveTabPaneContent();
        Assert.assertTrue(content.contains(expectedSnippet), "Tab pane missing: " + expectedSnippet);
    }

    @Then("the More tab should be disabled")
    public void verifyMoreTabDisabled() {
        Assert.assertTrue(tabsPage.isMoreTabDisabled(), "More tab is not disabled");
    }

    // --- TOOL TIPS ---
    @Given("I open the DemoQA Tool Tips page")
    public void openToolTipsPage() {
        String url = ConfigReader.get("app.tooltips.url");
        Log.info("[PLAYWRIGHT] Navigating to Tool Tips page: " + url);
        PlaywrightManager.navigate(url);
        toolTipsPage = new PlaywrightToolTipsPage();
    }

    @When("I hover over the tooltip button")
    public void hoverTooltipBtn() {
        toolTipsPage.hoverButton();
    }

    @Then("the tooltip text should be {string}")
    public void verifyTooltipText(String expected) {
        String actual = toolTipsPage.getTooltipText();
        Assert.assertEquals(actual, expected);
    }

    @When("I hover over the tooltip text field")
    public void hoverTooltipField() {
        toolTipsPage.hoverTextField();
    }

    // --- MENU ---
    @Given("I open the DemoQA Menu page")
    public void openMenuPage() {
        String url = ConfigReader.get("app.menu.url");
        Log.info("[PLAYWRIGHT] Navigating to Menu page: " + url);
        PlaywrightManager.navigate(url);
        menuPage = new PlaywrightMenuPage();
    }

    @When("I hover over Main Item 2 and SUB SUB LIST")
    public void hoverMenuHierarchy() {
        menuPage.hoverMenuHierarchy();
    }

    @Then("the sub sub menu item {string} should be visible")
    public void verifySubSubItemVisible(String subItemName) {
        Assert.assertTrue(menuPage.isSubSubItemDisplayed(), "Menu item not visible: " + subItemName);
    }
}
