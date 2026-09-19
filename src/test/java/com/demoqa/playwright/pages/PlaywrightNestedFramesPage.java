package com.demoqa.playwright.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;
import com.automation.utils.Log;

public class PlaywrightNestedFramesPage extends PlaywrightBasePage {

    public PlaywrightPageElement parentFrame;

    public PlaywrightNestedFramesPage() {
        super("PlaywrightNestedFramesPage");
    }

    public void validateAndHealPageElements() {
        Log.info("[PLAYWRIGHT] Validating select menu elements");
        super.validateAndHealPageElements();
    }

    @Override
    protected void initElements() {
        parentFrame = register("parentFrame", "Parent iFrame container", "#frame1");
    }

    public String getParentFrameText() {
        Log.info("[PLAYWRIGHT] Reading parent frame body text");
        return getPage().frameLocator("#frame1").locator("body").innerText().trim();
    }

    public String getChildIframeText() {
        Log.info("[PLAYWRIGHT] Reading child iframe paragraph text");
        return getPage().frameLocator("#frame1").frameLocator("iframe").locator("p").innerText().trim();
    }
}
