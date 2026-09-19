package com.demoqa.playwright.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;
import com.automation.utils.Log;

public class PlaywrightAlertsPage extends PlaywrightBasePage {

    public PlaywrightPageElement alertButton;
    public PlaywrightPageElement confirmButton;
    public PlaywrightPageElement promptButton;
    public PlaywrightPageElement confirmResult;
    public PlaywrightPageElement promptResult;

    public PlaywrightAlertsPage() {
        super("PlaywrightAlertsPage");
    }
    public void validateAndHealPageElements() {
        Log.info("[PLAYWRIGHT] Validating select menu elements");
        super.validateAndHealPageElements();
    }

    @Override
    protected void initElements() {
        // Diverse native Playwright locator strategies:
        alertButton = register("alertButton", "Simple alert trigger button", "button#alertButton"); // CSS ID
        confirmButton = register("confirmButton", "Confirm box trigger button", "//button[@id='confirmButton']"); // XPath Attribute
        promptButton = register("promptButton", "Prompt box trigger button", "button#promtButton"); // CSS ID
        confirmResult = register("confirmResult", "Confirm result message", "#confirmResult"); // CSS ID
        promptResult = register("promptResult", "Prompt result message", "span#promptResult"); // CSS ID
    }

    public void open() {
        navigate("https://demoqa.com/alerts");
    }

    public void triggerSimpleAlert() {
        Log.info("[PLAYWRIGHT] Triggering simple alert");
        getPage().onceDialog(dialog -> {
            Log.info("[PLAYWRIGHT] Dialog message: " + dialog.message());
            dialog.accept();
        });
        click(alertButton);
    }

    public void triggerConfirmAlert() {
        Log.info("[PLAYWRIGHT] Triggering confirm box");
        getPage().onceDialog(dialog -> {
            Log.info("[PLAYWRIGHT] Confirm message: " + dialog.message());
            dialog.accept();
        });
        click(confirmButton);
    }

    public String getConfirmResultText() {
        return getText(confirmResult);
    }

    public void triggerPromptAlert(String inputText) {
        Log.info("[PLAYWRIGHT] Triggering prompt alert with input: " + inputText);
        getPage().onceDialog(dialog -> {
            Log.info("[PLAYWRIGHT] Prompt message: " + dialog.message());
            dialog.accept(inputText);
        });
        click(promptButton);
    }

    public String getPromptResultText() {
        return getText(promptResult);
    }
}
