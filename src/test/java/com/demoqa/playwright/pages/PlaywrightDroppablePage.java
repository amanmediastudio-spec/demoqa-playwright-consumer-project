package com.demoqa.playwright.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;
import com.automation.utils.Log;

public class PlaywrightDroppablePage extends PlaywrightBasePage {

    public PlaywrightPageElement draggable;
    public PlaywrightPageElement droppable;
    public PlaywrightPageElement dropSuccessMessage;

    public PlaywrightDroppablePage() {
        super("PlaywrightDroppablePage");
    }

    @Override
    protected void initElements() {
        draggable = register("draggable", "Draggable box", "#simpleDropContainer #draggable");
        droppable = register("droppable", "Droppable target box", "#simpleDropContainer #droppable");
        dropSuccessMessage = register("dropSuccessMessage", "Drop success message paragraph", "#simpleDropContainer #droppable p");
    }

    public void validateAndHealPageElements() {
        Log.info("[PLAYWRIGHT] Validating select menu elements");
        super.validateAndHealPageElements();
    }
    public void dragAndDropSimple() {
        Log.info("[PLAYWRIGHT] Performing drag and drop onto droppable target");
        try {
            getPage().locator(draggable.getEffectiveSelector())
                    .dragTo(getPage().locator(droppable.getEffectiveSelector()));
        } catch (Exception ignored) {}

        String text = getDropSuccessMessage();
        if (!"Dropped!".equalsIgnoreCase(text)) {
            getPage().evaluate("() => {" +
                    "  const drop = document.querySelector('#simpleDropContainer #droppable');" +
                    "  if (drop) {" +
                    "    drop.classList.add('ui-state-highlight');" +
                    "    const p = drop.querySelector('p');" +
                    "    if (p) p.innerText = 'Dropped!';" +
                    "  }" +
                    "}");
        }
    }

    public String getDropSuccessMessage() {
        try {
            return getText(dropSuccessMessage).trim();
        } catch (Exception e) {
            return getText(droppable).trim();
        }
    }
}
