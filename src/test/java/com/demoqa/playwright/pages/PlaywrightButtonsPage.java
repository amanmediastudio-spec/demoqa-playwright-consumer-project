package com.demoqa.playwright.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;
import com.automation.utils.Log;

public class PlaywrightButtonsPage extends PlaywrightBasePage {

    public PlaywrightPageElement doubleClickBtn;
    public PlaywrightPageElement rightClickBtn;
    public PlaywrightPageElement dynamicClickBtn;
    public PlaywrightPageElement doubleClickMessage;
    public PlaywrightPageElement rightClickMessage;
    public PlaywrightPageElement dynamicClickMessage;

    public PlaywrightButtonsPage() {
        super("PlaywrightButtonsPage");
    }

    public void validateAndHealPageElements() {
        Log.info("[PLAYWRIGHT] Validating select menu elements");
        super.validateAndHealPageElements();
    }
    @Override
    protected void initElements() {
        // Diverse native Playwright locator strategies:
        doubleClickBtn = register("doubleClickBtn", "Double click button", "text=\"Double Click Me\""); // Playwright Text Engine
        rightClickBtn = register("rightClickBtn", "Right click button", "button#rightClickBtn"); // CSS ID
        dynamicClickBtn = register("dynamicClickBtn", "Dynamic click me button", "//button[text()='Click Me']"); // XPath Text Match
        doubleClickMessage = register("doubleClickMessage", "Double click message", "#doubleClickMessage"); // CSS ID
        rightClickMessage = register("rightClickMessage", "Right click message", "#rightClickMessage"); // CSS ID
        dynamicClickMessage = register("dynamicClickMessage", "Dynamic click success text message", "#dynamicClickMessage"); // CSS ID
    }

    public void simulateBrokenDoubleClickLocator() {
        doubleClickBtn.setBrokenSelectorForSimulation("button#broken_doubleClickBtn_9999");
    }

    public void simulateBrokenRightClickLocator() {
        rightClickBtn.setBrokenSelectorForSimulation("button#broken_rightClickBtn_8888");
    }

    public void simulateBrokenDynamicClickLocator() {
        dynamicClickBtn.setBrokenSelectorForSimulation("//button[text()='broken_click_me_7777']");
    }

    public void open() {
        navigate("https://demoqa.com/buttons");
    }

    public void performDoubleClick() {
        Log.info("[PLAYWRIGHT] Performing double click");
        dblclick(doubleClickBtn);
    }

    public String getDoubleClickMessage() {
        return getText(doubleClickMessage);
    }

    public void performRightClick() {
        Log.info("[PLAYWRIGHT] Performing right click");
        rightClick(rightClickBtn);
    }

    public String getRightClickMessage() {
        return getText(rightClickMessage);
    }

    public void performDynamicClick() {
        Log.info("[PLAYWRIGHT] Performing dynamic click");
        click(dynamicClickBtn);
    }

    public String getDynamicClickMessage() {
        return getText(dynamicClickMessage);
    }
}
