package com.demoqa.playwright.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;
import com.automation.utils.Log;

public class PlaywrightFramesPage extends PlaywrightBasePage {

    public PlaywrightPageElement frame1;
    public PlaywrightPageElement frame2;

    public PlaywrightFramesPage() {
        super("PlaywrightFramesPage");
    }

    public void validateAndHealPageElements() {
        Log.info("[PLAYWRIGHT] Validating select menu elements");
        super.validateAndHealPageElements();
    }
    @Override
    protected void initElements() {
        frame1 = register("frame1", "First iFrame container", "#frame1");
        frame2 = register("frame2", "Second iFrame container", "#frame2");
    }

    public String getTextFromFrame(String frameId) {
        Log.info("[PLAYWRIGHT] Extracting text from frame: " + frameId);
        return getPage().frameLocator("#" + frameId).locator("#sampleHeading").innerText().trim();
    }
}
