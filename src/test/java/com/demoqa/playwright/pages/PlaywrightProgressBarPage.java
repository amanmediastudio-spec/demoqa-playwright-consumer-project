package com.demoqa.playwright.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;
import com.automation.utils.Log;
import com.microsoft.playwright.Page;

public class PlaywrightProgressBarPage extends PlaywrightBasePage {

    public PlaywrightPageElement startStopBtn;
    public PlaywrightPageElement resetBtn;
    public PlaywrightPageElement progressBar;

    public PlaywrightProgressBarPage() {
        super("PlaywrightProgressBarPage");
    }

    @Override
    protected void initElements() {
        startStopBtn = register("startStopBtn", "Start or Stop progress bar button", "#startStopButton");
        resetBtn = register("resetBtn", "Reset progress bar button", "#resetButton");
        progressBar = register("progressBar", "Progress bar indicator", "#progressBar .progress-bar, div[role='progressbar']");
    }

    public void validateAndHealPageElements() {
        Log.info("[PLAYWRIGHT] Validating select menu elements");
        super.validateAndHealPageElements();
    }
    
    public void startProgressBar() {
        Log.info("[PLAYWRIGHT] Starting progress bar");
        click(startStopBtn);
    }

    public void waitForCompletion(int timeoutSeconds) {
        Log.info("[PLAYWRIGHT] Waiting for progress bar to reach 100%");
        try {
            getPage().waitForFunction(
                    "() => { const bar = document.querySelector(\"#progressBar .progress-bar, div[role='progressbar']\"); return bar && bar.getAttribute('aria-valuenow') === '100'; }",
                    null,
                    new Page.WaitForFunctionOptions().setTimeout(timeoutSeconds * 1000.0)
            );
        } catch (Exception e) {
            Log.warn("[PLAYWRIGHT] Timeout waiting for progress bar completion: " + e.getMessage());
        }
    }

    public int getProgressBarValue() {
        String val = getAttribute(progressBar, "aria-valuenow");
        return Integer.parseInt(val.trim());
    }

    public void resetProgressBar() {
        Log.info("[PLAYWRIGHT] Resetting progress bar");
        click(resetBtn);
    }
}
