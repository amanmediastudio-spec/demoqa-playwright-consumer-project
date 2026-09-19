package com.demoqa.playwright.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;
import com.automation.utils.Log;

public class PlaywrightToolTipsPage extends PlaywrightBasePage {

    public PlaywrightPageElement toolTipButton;
    public PlaywrightPageElement toolTipTextField;

    public PlaywrightToolTipsPage() {
        super("PlaywrightToolTipsPage");
    }

    @Override
    protected void initElements() {
        toolTipButton = register("toolTipButton", "Hover me to see button", "#toolTipButton");
        toolTipTextField = register("toolTipTextField", "Hover me to see input field", "#toolTipTextField");
    }
    public void validateAndHealPageElements() {
        Log.info("[PLAYWRIGHT] Validating select menu elements");
        super.validateAndHealPageElements();
    }
    public void hoverButton() {
        Log.info("[PLAYWRIGHT] Hovering over button");
        scrollIntoView(toolTipButton);
        getPage().waitForTimeout(300);
        hover(toolTipButton);
        getPage().waitForTimeout(600);
    }

    public void hoverTextField() {
        Log.info("[PLAYWRIGHT] Hovering over text field");
        scrollIntoView(toolTipTextField);
        getPage().waitForTimeout(300);
        hover(toolTipTextField);
        getPage().waitForTimeout(600);
    }

    public String getTooltipText() {
        try {
            getPage().waitForSelector(".tooltip.show .tooltip-inner, div[role='tooltip'].show, .tooltip-inner",
                    new com.microsoft.playwright.Page.WaitForSelectorOptions().setTimeout(6000));
        } catch (Exception ignored) {}

        return (String) getPage().evaluate("() => {" +
                "  const show = document.querySelector('.tooltip.show .tooltip-inner, div[role=\"tooltip\"].show .tooltip-inner');" +
                "  if (show && show.innerText.trim().length > 0) return show.innerText.trim();" +
                "  const btnTip = document.querySelector('#buttonToolTip .tooltip-inner');" +
                "  if (btnTip && btnTip.innerText.trim().length > 0) return btnTip.innerText.trim();" +
                "  const fieldTip = document.querySelector('#textFieldToolTip .tooltip-inner');" +
                "  if (fieldTip && fieldTip.innerText.trim().length > 0) return fieldTip.innerText.trim();" +
                "  const inners = Array.from(document.querySelectorAll('.tooltip-inner'));" +
                "  const active = inners.filter(i => i.offsetParent !== null && i.innerText.trim().length > 0).pop();" +
                "  if (active) return active.innerText.trim();" +
                "  return inners.length > 0 ? inners[inners.length - 1].innerText.trim() : '';" +
                "}");
    }
}
