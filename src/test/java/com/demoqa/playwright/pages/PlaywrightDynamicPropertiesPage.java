package com.demoqa.playwright.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;
import com.automation.utils.Log;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class PlaywrightDynamicPropertiesPage extends PlaywrightBasePage {

    public PlaywrightPageElement enableAfterBtn;
    public PlaywrightPageElement colorChangeBtn;
    public PlaywrightPageElement visibleAfterBtn;

    public PlaywrightDynamicPropertiesPage() {
        super("PlaywrightDynamicPropertiesPage");
    }

    @Override
    protected void initElements() {
        enableAfterBtn = register("enableAfterBtn", "Will enable 5 seconds button", "#enableAfter");
        colorChangeBtn = register("colorChangeBtn", "Color change button", "#colorChange");
        visibleAfterBtn = register("visibleAfterBtn", "Visible after 5 seconds button", "#visibleAfter");
    }

    public void validateAndHealPageElements() {
        Log.info("[PLAYWRIGHT] Validating select menu elements");
        super.validateAndHealPageElements();
    }
    public boolean waitForButtonToBeEnabled(int timeoutSeconds) {
        Log.info("[PLAYWRIGHT] Waiting for enableAfter button to be enabled (up to " + timeoutSeconds + "s)");
        Locator btn = getPage().locator(enableAfterBtn.getEffectiveSelector());
        btn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(timeoutSeconds * 1000.0));
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < timeoutSeconds * 1000L) {
            if (btn.isEnabled()) {
                return true;
            }
            getPage().waitForTimeout(200);
        }
        return btn.isEnabled();
    }

    public String getColorChangeButtonClass() {
        return getAttribute(colorChangeBtn, "class");
    }

    public boolean waitForColorChange(int timeoutSeconds) {
        Log.info("[PLAYWRIGHT] Waiting for color change button to get danger/red styling");
        try {
            getPage().waitForFunction(
                    "() => { const b = document.querySelector('#colorChange'); return b && b.classList.contains('text-danger'); }",
                    null,
                    new Page.WaitForFunctionOptions().setTimeout(timeoutSeconds * 1000.0)
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean waitForButtonToBeVisible(int timeoutSeconds) {
        Log.info("[PLAYWRIGHT] Waiting for visibleAfter button to appear");
        try {
            Locator btn = getPage().locator(visibleAfterBtn.getEffectiveSelector());
            btn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(timeoutSeconds * 1000.0));
            return btn.isVisible();
        } catch (Exception e) {
            return false;
        }
    }
}
