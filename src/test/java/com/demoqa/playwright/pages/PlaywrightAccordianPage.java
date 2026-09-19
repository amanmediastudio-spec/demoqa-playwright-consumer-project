package com.demoqa.playwright.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;
import com.automation.utils.Log;

public class PlaywrightAccordianPage extends PlaywrightBasePage {

    public PlaywrightPageElement heading1;
    public PlaywrightPageElement heading2;
    public PlaywrightPageElement heading3;

    public PlaywrightAccordianPage() {
        super("PlaywrightAccordianPage");
    }

    public void validateAndHealPageElements() {
        Log.info("[PLAYWRIGHT] Validating select menu elements");
        super.validateAndHealPageElements();
    }

    @Override
    protected void initElements() {
        heading1 = register("heading1", "Section 1 Heading", ".accordion-item:nth-of-type(1) .accordion-button");
        heading2 = register("heading2", "Section 2 Heading", ".accordion-item:nth-of-type(2) .accordion-button");
        heading3 = register("heading3", "Section 3 Heading", ".accordion-item:nth-of-type(3) .accordion-button");
    }

    public void clickSectionHeading(int section) {
        Log.info("[PLAYWRIGHT] Clicking Accordian heading section: " + section);
        PlaywrightPageElement heading = switch (section) {
            case 1 -> heading1;
            case 2 -> heading2;
            case 3 -> heading3;
            default -> register("heading_" + section, "Heading section " + section, ".accordion-item:nth-of-type(" + section + ") .accordion-button");
        };
        click(heading);
        getPage().waitForTimeout(500);
    }

    public boolean isSectionContentDisplayed(int section) {
        try {
            return (Boolean) getPage().evaluate("idx => {" +
                    "  const items = document.querySelectorAll('.accordion-item');" +
                    "  if (!items[idx]) return false;" +
                    "  const btn = items[idx].querySelector('button.accordion-button');" +
                    "  const collapse = items[idx].querySelector('.accordion-collapse');" +
                    "  return btn?.getAttribute('aria-expanded') === 'true' || collapse?.classList.contains('show');" +
                    "}", section - 1);
        } catch (Exception e) {
            return false;
        }
    }

    public String getSectionContentText(int section) {
        PlaywrightPageElement body = register("body_" + section, "Body section " + section, ".accordion-item:nth-of-type(" + section + ") .accordion-body");
        return getText(body).trim();
    }
}
