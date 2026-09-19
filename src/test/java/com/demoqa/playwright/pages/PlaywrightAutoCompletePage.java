package com.demoqa.playwright.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;
import com.automation.utils.Log;

import java.util.List;

public class PlaywrightAutoCompletePage extends PlaywrightBasePage {

    public PlaywrightPageElement multipleInput;
    public PlaywrightPageElement singleInput;

    public PlaywrightAutoCompletePage() {
        super("PlaywrightAutoCompletePage");
    }

    public void validateAndHealPageElements() {
        Log.info("[PLAYWRIGHT] Validating select menu elements");
        super.validateAndHealPageElements();
    }

    @Override
    protected void initElements() {
        multipleInput = register("multipleInput", "Multiple color autocomplete input", "#autoCompleteMultipleInput");
        singleInput = register("singleInput", "Single color autocomplete input", "#autoCompleteSingleInput");
    }

    public void addMultipleColors(List<String> colors) {
        Log.info("[PLAYWRIGHT] Adding multiple colors: " + colors);
        for (String color : colors) {
            click(multipleInput);
            pressSequentially(multipleInput, color);
            getPage().waitForTimeout(300);
            getPage().keyboard().press("Enter");
            getPage().waitForTimeout(200);
        }
    }

    public List<String> getSelectedMultipleColors() {
        return getPage().locator(".auto-complete__multi-value__label").allInnerTexts();
    }

    public void removeMultipleColorBadge(String color) {
        Log.info("[PLAYWRIGHT] Removing color badge: " + color);
        PlaywrightPageElement removeBadge = register("removeBadge_" + color, "Remove badge for " + color, "//div[contains(@class,'auto-complete__multi-value') and .//div[text()='" + color + "']]//div[contains(@class,'remove')]");
        click(removeBadge);
    }

    public void selectSingleColor(String color) {
        Log.info("[PLAYWRIGHT] Selecting single color: " + color);
        click(singleInput);
        pressSequentially(singleInput, color);
        getPage().waitForTimeout(400);
        getPage().keyboard().press("Enter");
        getPage().waitForTimeout(300);
    }

    public String getSelectedSingleColor() {
        try {
            return getPage().locator(".auto-complete__single-value, div[class*='singleValue']").innerText().trim();
        } catch (Exception e) {
            return (String) getPage().evaluate("() => document.querySelector('.auto-complete__single-value, div[class*=\"singleValue\"]')?.innerText || ''");
        }
    }
}
