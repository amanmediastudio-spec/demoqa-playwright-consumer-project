package com.demoqa.playwright.stepdefinitions;

import com.automation.config.ConfigReader;
import com.automation.playwright.PlaywrightManager;
import com.automation.utils.Log;
import com.demoqa.playwright.pages.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.File;
import java.util.List;
import java.util.Map;

public class PlaywrightElementsSteps {

    private PlaywrightTextBoxPage textBoxPage;
    private PlaywrightCheckBoxPage checkBoxPage;
    private PlaywrightRadioButtonPage radioButtonPage;
    private PlaywrightButtonsPage buttonsPage;
    private PlaywrightWebTablesPage webTablesPage;
    private PlaywrightLinksPage linksPage;
    private PlaywrightBrokenLinksImagesPage brokenPage;
    private PlaywrightUploadDownloadPage uploadDownloadPage;
    private PlaywrightDynamicPropertiesPage dynamicPropertiesPage;
    private final ObjectMapper mapper = new ObjectMapper();

    // ==========================================
    // --- TEXT BOX ---
    // ==========================================
    @Given("I open the DemoQA Text Box page")
    public void openTextBoxPage() {
        String url = ConfigReader.get("app.textbox.url");
        Log.info("[PLAYWRIGHT] Navigating to Text Box page: " + url);
        PlaywrightManager.navigate(url);
        textBoxPage = new PlaywrightTextBoxPage();
    }

    @When("I submit the text box form using profile {string} from JSON")
    public void submitTextBoxWithProfile(String profileKey) throws Exception {
        Map<String, Map<String, Object>> data = mapper.readValue(
                new File("src/test/resources/data/user_form_data.json"),
                new TypeReference<Map<String, Map<String, Object>>>() {}
        );
        Map<String, Object> userProfile = data.get(profileKey);

        textBoxPage.fillForm(
                (String) userProfile.get("fullName"),
                (String) userProfile.get("email"),
                (String) userProfile.get("currentAddress"),
                (String) userProfile.get("permanentAddress")
        );
        textBoxPage.submitForm();
    }

    @Then("the output card should contain {string}")
    public void verifyOutputCard(String expectedText) {
        String output = textBoxPage.getOutputText();
        Log.info("[PLAYWRIGHT] Output card details: \n" + output);
        Assert.assertTrue(output.contains(expectedText), "Output missing expected text: " + expectedText);
    }

    // ==========================================
    // --- CHECK BOX ---
    // ==========================================
    @Given("I open the DemoQA Check Box page")
    public void openCheckBoxPage() {
        String url = ConfigReader.get("app.checkbox.url");
        Log.info("[PLAYWRIGHT] Navigating to Check Box page: " + url);
        PlaywrightManager.navigate(url);
        checkBoxPage = new PlaywrightCheckBoxPage();
    }

    @When("I expand all checkbox tree nodes")
    public void expandAllNodes() {
        checkBoxPage.expandAllNodes();
    }

    @When("I collapse all checkbox tree nodes")
    public void collapseAllNodes() {
        checkBoxPage.collapseAllNodes();
    }

    @When("I toggle the checkbox node {string}")
    public void toggleCheckboxNode(String nodeName) {
        checkBoxPage.toggleNode(nodeName);
    }

    @Then("the checkbox result display should contain {string}")
    public void verifyCheckboxResult(String expectedKeyword) {
        String res = checkBoxPage.getResultText();
        Log.info("[PLAYWRIGHT] Checkbox result text: " + res);
        Assert.assertTrue(res.toLowerCase().contains(expectedKeyword.toLowerCase()),
                "Checkbox result missing keyword: " + expectedKeyword);
    }

    // ==========================================
    // --- RADIO BUTTON ---
    // ==========================================
    @Given("I open the DemoQA Radio Button page")
    public void openRadioButtonPage() {
        String url = ConfigReader.get("app.radio.url");
        Log.info("[PLAYWRIGHT] Navigating to Radio Button page: " + url);
        PlaywrightManager.navigate(url);
        radioButtonPage = new PlaywrightRadioButtonPage();
    }

    @When("I select the {string} radio button")
    public void selectRadioButton(String radioType) {
        if ("Yes".equalsIgnoreCase(radioType)) {
            radioButtonPage.selectYesRadio();
        } else if ("Impressive".equalsIgnoreCase(radioType)) {
            radioButtonPage.selectImpressiveRadio();
        }
    }

    @Then("the radio selection result text should display {string}")
    public void verifyRadioResult(String expectedResult) {
        String actual = radioButtonPage.getSelectedResultText();
        Log.info("[PLAYWRIGHT] Verified radio result: " + actual);
        Assert.assertEquals(actual, expectedResult);
    }

    // ==========================================
    // --- BUTTONS ---
    // ==========================================
    @Given("I open the DemoQA Buttons page")
    public void openButtonsPage() {
        String url = ConfigReader.get("app.buttons.url");
        Log.info("[PLAYWRIGHT] Navigating to Buttons page: " + url);
        PlaywrightManager.navigate(url);
        buttonsPage = new PlaywrightButtonsPage();
    }

    @When("I perform a double click on the Double Click button")
    public void performDoubleClick() {
        buttonsPage.performDoubleClick();
    }

    @Then("the double click message should display {string}")
    public void verifyDoubleClickMsg(String expected) {
        String actual = buttonsPage.getDoubleClickMessage();
        Assert.assertEquals(actual, expected);
    }

    @When("I perform a right click on the Right Click button")
    public void performRightClick() {
        buttonsPage.performRightClick();
    }

    @Then("the right click message should display {string}")
    public void verifyRightClickMsg(String expected) {
        String actual = buttonsPage.getRightClickMessage();
        Assert.assertEquals(actual, expected);
    }

    @When("I perform a standard click on the Dynamic Click button")
    public void performDynamicClick() {
        buttonsPage.performDynamicClick();
    }

    @Then("the dynamic click message should display {string}")
    public void verifyDynamicClickMsg(String expected) {
        String actual = buttonsPage.getDynamicClickMessage();
        Assert.assertEquals(actual, expected);
    }

    // ==========================================
    // --- WEB TABLES ---
    // ==========================================
    @Given("I open the DemoQA Web Tables page")
    public void openWebTablesPage() {
        String url = ConfigReader.get("app.webtables.url");
        Log.info("[PLAYWRIGHT] Navigating to Web Tables page: " + url);
        PlaywrightManager.navigate(url);
        webTablesPage = new PlaywrightWebTablesPage();
    }

    @When("I search the table for {string}")
    public void searchWebTable(String query) {
        webTablesPage.searchTable(query);
    }

    @Then("the table rows should contain {string}")
    public void verifyTableRowContains(String expectedText) {
        List<String> rows = webTablesPage.getTableRowsText();
        boolean found = rows.stream().anyMatch(r -> r.contains(expectedText));
        Log.info("[PLAYWRIGHT] Web table rows searched: " + rows);
        Assert.assertTrue(found, "Expected text [" + expectedText + "] not found in table rows.");
    }

    @When("I add a new record using profile {string} from JSON")
    public void addNewRecord(String profileKey) throws Exception {
        Map<String, Map<String, Object>> data = mapper.readValue(
                new File("src/test/resources/data/user_form_data.json"),
                new TypeReference<Map<String, Map<String, Object>>>() {}
        );
        Map<String, Object> employee = data.get(profileKey);

        webTablesPage.clickAddNewRecord();
        webTablesPage.fillRegistrationForm(
                (String) employee.get("firstName"),
                (String) employee.get("lastName"),
                (String) employee.get("email"),
                (String) employee.get("age"),
                (String) employee.get("salary"),
                (String) employee.get("department")
        );
    }

    @When("I edit the record for {string} with salary {string} and department {string}")
    public void editRecord(String firstName, String salary, String dept) {
        webTablesPage.editRecord(firstName, salary, dept);
    }

    @When("I delete the record for {string}")
    public void deleteRecord(String firstName) {
        webTablesPage.deleteRecord(firstName);
    }

    @When("I select rows per page as {string}")
    public void selectRowsPerPage(String rowsCount) {
        webTablesPage.selectRowsPerPage(rowsCount);
    }

    // ==========================================
    // --- LINKS ---
    // ==========================================
    @Given("I open the DemoQA Links page")
    public void openLinksPage() {
        String url = ConfigReader.get("app.links.url");
        Log.info("[PLAYWRIGHT] Navigating to Links page: " + url);
        PlaywrightManager.navigate(url);
        linksPage = new PlaywrightLinksPage();
    }

    @When("I click the Simple link and switch to new tab")
    public void clickSimpleLink() {
        linksPage.clickSimpleLinkAndSwitchTab();
    }

    @Then("the new tab URL should contain {string}")
    public void verifyNewTabUrl(String expectedPart) {
        String childUrl = linksPage.getChildPageUrl();
        Log.info("[PLAYWRIGHT] Verified new tab URL: " + childUrl);
        Assert.assertTrue(childUrl.contains(expectedPart), "URL does not contain: " + expectedPart);
    }

    @When("I click the API link {string}")
    public void clickApiLink(String linkName) {
        linksPage.clickApiLink(linkName);
    }

    @Then("the link status response text should contain {string}")
    public void verifyLinkResponse(String expectedStatus) {
        String resp = linksPage.getLinkResponseText();
        Log.info("[PLAYWRIGHT] Link API response: " + resp);
        Assert.assertTrue(resp.contains(expectedStatus), "Response missing status: " + expectedStatus);
    }

    // ==========================================
    // --- BROKEN LINKS & IMAGES ---
    // ==========================================
    @Given("I open the DemoQA Broken Links and Images page")
    public void openBrokenPage() {
        String url = ConfigReader.get("app.broken.url");
        Log.info("[PLAYWRIGHT] Navigating to Broken Links page: " + url);
        PlaywrightManager.navigate(url);
        brokenPage = new PlaywrightBrokenLinksImagesPage();
    }

    @Then("the valid image should be rendered successfully")
    public void verifyValidImage() {
        Assert.assertTrue(brokenPage.isValidImageDisplayed(), "Valid image failed to render naturalWidth > 0");
    }

    @Then("the broken image should be detected as broken")
    public void verifyBrokenImage() {
        Assert.assertTrue(brokenPage.isBrokenImageDetected(), "Broken image was not detected as broken");
    }

    @When("I click the valid link on broken page")
    public void clickValidLinkOnBrokenPage() {
        brokenPage.clickValidLink();
    }

    @When("I click the broken link on broken page")
    public void clickBrokenLinkOnBrokenPage() {
        brokenPage.clickBrokenLink();
    }

    // ==========================================
    // --- UPLOAD & DOWNLOAD ---
    // ==========================================
    @Given("I open the DemoQA Upload and Download page")
    public void openUploadDownloadPage() {
        String url = ConfigReader.get("app.uploaddownload.url");
        Log.info("[PLAYWRIGHT] Navigating to Upload Download page: " + url);
        PlaywrightManager.navigate(url);
        uploadDownloadPage = new PlaywrightUploadDownloadPage();
    }

    @When("I click the download button")
    public void clickDownloadBtn() {
        uploadDownloadPage.clickDownloadButton();
    }

    @When("I upload the file {string}")
    public void uploadFile(String filePath) {
        uploadDownloadPage.uploadFile(filePath);
    }

    @Then("the uploaded file path display should contain {string}")
    public void verifyUploadedFilePath(String expectedFileName) {
        String actual = uploadDownloadPage.getUploadedFilePathText();
        Log.info("[PLAYWRIGHT] Uploaded path display: " + actual);
        Assert.assertTrue(actual.contains(expectedFileName), "Uploaded path does not contain: " + expectedFileName);
    }

    // ==========================================
    // --- DYNAMIC PROPERTIES ---
    // ==========================================
    @Given("I open the DemoQA Dynamic Properties page")
    public void openDynamicPropertiesPage() {
        String url = ConfigReader.get("app.dynamicproperties.url");
        Log.info("[PLAYWRIGHT] Navigating to Dynamic Properties page: " + url);
        PlaywrightManager.navigate(url);
        dynamicPropertiesPage = new PlaywrightDynamicPropertiesPage();
    }

    @Then("the enable after button should become clickable within {int} seconds")
    public void verifyEnableAfter(int timeoutSeconds) {
        boolean enabled = dynamicPropertiesPage.waitForButtonToBeEnabled(timeoutSeconds);
        Assert.assertTrue(enabled, "Enable after button did not become clickable within " + timeoutSeconds + "s");
    }

    @Then("the color change button should transition color within {int} seconds")
    public void verifyColorChange(int timeoutSeconds) {
        boolean changed = dynamicPropertiesPage.waitForColorChange(timeoutSeconds);
        Assert.assertTrue(changed, "Color change button did not change class to text-danger");
    }

    @Then("the visible after button should become visible within {int} seconds")
    public void verifyVisibleAfter(int timeoutSeconds) {
        boolean visible = dynamicPropertiesPage.waitForButtonToBeVisible(timeoutSeconds);
        Assert.assertTrue(visible, "Visible after button did not appear within " + timeoutSeconds + "s");
    }
}
