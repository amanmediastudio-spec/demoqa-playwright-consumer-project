package com.demoqa.playwright.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;
import com.automation.utils.Log;
import com.microsoft.playwright.Page;

public class PlaywrightBrowserWindowsPage extends PlaywrightBasePage {

    public PlaywrightPageElement tabButton;
    public PlaywrightPageElement windowButton;
    public PlaywrightPageElement sampleHeading;

    private Page childPage;

    public PlaywrightBrowserWindowsPage() {
        super("PlaywrightBrowserWindowsPage");
    }

    public void validateAndHealPageElements() {
        Log.info("[PLAYWRIGHT] Validating select menu elements");
        super.validateAndHealPageElements();
    }

    @Override
    protected void initElements() {
        tabButton = register("tabButton", "New Tab trigger button", "#tabButton");
        windowButton = register("windowButton", "New Window trigger button", "#windowButton");
        sampleHeading = register("sampleHeading", "Sample heading text in new window/tab", "#sampleHeading");
    }

    public void openNewTab() {
        Log.info("[PLAYWRIGHT] Opening new tab");
        childPage = getPage().waitForPopup(() -> {
            click(tabButton);
        });
        childPage.waitForLoadState();
    }

    public void openNewWindow() {
        Log.info("[PLAYWRIGHT] Opening new window");
        childPage = getPage().waitForPopup(() -> {
            click(windowButton);
        });
        childPage.waitForLoadState();
    }

    public String switchToChildWindowAndGetHeading() {
        if (childPage != null) {
            String heading = childPage.locator(sampleHeading.getEffectiveSelector()).innerText().trim();
            childPage.close();
            childPage = null;
            return heading;
        }
        return getText(sampleHeading).trim();
    }
}
