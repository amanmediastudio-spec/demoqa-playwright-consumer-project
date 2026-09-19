package com.demoqa.playwright.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;
import com.automation.utils.Log;

public class PlaywrightLoginPage extends PlaywrightBasePage {

    public PlaywrightPageElement userNameInput;
    public PlaywrightPageElement passwordInput;
    public PlaywrightPageElement loginButton;
    public PlaywrightPageElement errorMessage;

    public PlaywrightLoginPage() {
        super("PlaywrightLoginPage");
    }

    @Override
    protected void initElements() {
        userNameInput = register("userNameInput", "Login user name input", "#userName");
        passwordInput = register("passwordInput", "Login password input", "#password");
        loginButton = register("loginButton", "Login submit button", "#login");
        errorMessage = register("errorMessage", "Invalid login error message output", "#name");
    }

    public void validateAndHealPageElements() {
        Log.info("[PLAYWRIGHT] Validating select menu elements");
        super.validateAndHealPageElements();
    }

    public void login(String username, String password) {
        Log.info("[PLAYWRIGHT] Attempting login with username: " + username);
        fill(userNameInput, username);
        fill(passwordInput, password);
        click(loginButton);
    }

    public String getErrorMessageText() {
        waitForSelector(errorMessage, 5000);
        return getText(errorMessage).trim();
    }
}
