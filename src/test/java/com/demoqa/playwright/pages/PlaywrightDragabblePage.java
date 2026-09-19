package com.demoqa.playwright.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;
import com.automation.utils.Log;
import com.microsoft.playwright.Locator;

public class PlaywrightDragabblePage extends PlaywrightBasePage {

    public PlaywrightPageElement dragBox;

    public PlaywrightDragabblePage() {
        super("PlaywrightDragabblePage");
    }

    @Override
    protected void initElements() {
        dragBox = register("dragBox", "Simple drag box", "#dragBox");
    }

    public void validateAndHealPageElements() {
        Log.info("[PLAYWRIGHT] Validating select menu elements");
        super.validateAndHealPageElements();
    }

    public void dragSimpleBox(int xOffset, int yOffset) {
        Log.info("[PLAYWRIGHT] Dragging simple box by offset: " + xOffset + ", " + yOffset);
        Locator box = getPage().locator(dragBox.getEffectiveSelector());
        com.microsoft.playwright.options.BoundingBox bBox = box.boundingBox();
        if (bBox != null) {
            getPage().mouse().move(bBox.x + bBox.width / 2, bBox.y + bBox.height / 2);
            getPage().mouse().down();
            getPage().mouse().move(bBox.x + bBox.width / 2 + xOffset, bBox.y + bBox.height / 2 + yOffset);
            getPage().mouse().up();
        }
    }

    public double getDragBoxX() {
        com.microsoft.playwright.options.BoundingBox bBox = getPage().locator(dragBox.getEffectiveSelector()).boundingBox();
        return bBox != null ? bBox.x : 0;
    }

    public double getDragBoxY() {
        com.microsoft.playwright.options.BoundingBox bBox = getPage().locator(dragBox.getEffectiveSelector()).boundingBox();
        return bBox != null ? bBox.y : 0;
    }
}
