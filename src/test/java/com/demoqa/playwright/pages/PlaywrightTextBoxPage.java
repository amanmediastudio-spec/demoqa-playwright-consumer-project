package com.demoqa.playwright.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;
import com.automation.utils.Log;

public class PlaywrightTextBoxPage extends PlaywrightBasePage {

    public PlaywrightPageElement userNameInput;
    public PlaywrightPageElement userEmailInput;
    public PlaywrightPageElement currentAddressInput;
    public PlaywrightPageElement permanentAddressInput;
    public PlaywrightPageElement submitButton;
    public PlaywrightPageElement outputBox;

    public PlaywrightTextBoxPage() {
        super("PlaywrightTextBoxPage");
    }

    @Override
    protected void initElements() {
        // Diverse native Playwright locator strategies demonstrated across elements:
        userNameInput = register("userNameInput", "Full Name text input", "#userName"); // CSS ID
        userEmailInput = register("userEmailInput", "Email text input", "//input[@id='userEmail']"); // XPath
        currentAddressInput = register("currentAddressInput", "Current Address textarea", "#userForm >> textarea#currentAddress"); // Playwright Chained Combinator (>>)
        permanentAddressInput = register("permanentAddressInput", "Permanent Address textarea", "div#permanentAddress-wrapper textarea"); // CSS Hierarchy
        submitButton = register("submitButton", "Form submit button", "button:has-text('Submit')"); // Playwright Pseudo-Class (:has-text)
        outputBox = register("outputBox", "Submitted output card box", "div#output"); // CSS ID
    }

    public void validateAndHealPageElements() {
        Log.info("[PLAYWRIGHT] Validating select menu elements");
        super.validateAndHealPageElements();
    }

    public void open() {
        navigate("https://demoqa.com/text-box");
    }

    public void fillForm(String fullName, String email, String currentAddress, String permanentAddress) {
        enterFullName(fullName);
        enterEmail(email);
        enterCurrentAddress(currentAddress);
        enterPermanentAddress(permanentAddress);
    }

    public void enterFullName(String name) {
        fill(userNameInput, name);
    }

    public void enterEmail(String email) {
        fill(userEmailInput, email);
    }

    public void enterCurrentAddress(String address) {
        fill(currentAddressInput, address);
    }

    public void enterPermanentAddress(String address) {
        fill(permanentAddressInput, address);
    }

    public void submitForm() {
        getPage().evaluate("() => { const btn = document.querySelector('button#submit'); if (btn) btn.scrollIntoView({block: 'center'}); }");
        click(submitButton);
    }

    public String getOutputText() {
        return getText(outputBox);
    }

    public void simulateBrokenUserNameLocator() {
        userNameInput.setBrokenSelectorForSimulation("input#invalid_broken_playwright_username_99999");
    }

    public void simulateBrokenSubmitLocator() {
        submitButton.setBrokenSelectorForSimulation("button#invalid_broken_playwright_submit_88888");
    }
}
