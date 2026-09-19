package com.demoqa.playwright.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;
import com.automation.utils.Log;

public class PlaywrightModalDialogsPage extends PlaywrightBasePage {

    public PlaywrightPageElement showSmallModalBtn;
    public PlaywrightPageElement showLargeModalBtn;
    public PlaywrightPageElement smallModalTitle;
    public PlaywrightPageElement largeModalTitle;
    public PlaywrightPageElement closeSmallModalBtn;
    public PlaywrightPageElement closeLargeModalBtn;

    public PlaywrightModalDialogsPage() {
        super("PlaywrightModalDialogsPage");
    }

    public void validateAndHealPageElements() {
        Log.info("[PLAYWRIGHT] Validating select menu elements");
        super.validateAndHealPageElements();
    }

    @Override
    protected void initElements() {
        // Diverse native Playwright locator strategies:
        showSmallModalBtn = register("showSmallModalBtn", "Small Modal trigger button", "button:has-text('Small Modal')"); // Playwright Pseudo-Class (:has-text)
        showLargeModalBtn = register("showLargeModalBtn", "Large Modal trigger button", "button:has-text('Large Modal')"); // Playwright Pseudo-Class (:has-text)
        smallModalTitle = register("smallModalTitle", "Small Modal title header", "#example-modal-sizes-title-sm"); // CSS ID
        largeModalTitle = register("largeModalTitle", "Large Modal title header", "#example-modal-sizes-title-lg"); // CSS ID
        closeSmallModalBtn = register("closeSmallModalBtn", "Close Small Modal button", "#closeSmallModal"); // CSS ID
        closeLargeModalBtn = register("closeLargeModalBtn", "Close Large Modal button", "#closeLargeModal"); // CSS ID
    }

    public void openSmallModal() {
        Log.info("[PLAYWRIGHT] Opening Small Modal");
        click(showSmallModalBtn);
    }

    public String getSmallModalTitle() {
        waitForSelector(smallModalTitle, 5000);
        return getText(smallModalTitle).trim();
    }

    public void closeSmallModal() {
        Log.info("[PLAYWRIGHT] Closing Small Modal");
        click(closeSmallModalBtn);
    }

    public void openLargeModal() {
        Log.info("[PLAYWRIGHT] Opening Large Modal");
        click(showLargeModalBtn);
    }

    public String getLargeModalTitle() {
        waitForSelector(largeModalTitle, 5000);
        return getText(largeModalTitle).trim();
    }

    public void closeLargeModal() {
        Log.info("[PLAYWRIGHT] Closing Large Modal");
        click(closeLargeModalBtn);
    }
}
