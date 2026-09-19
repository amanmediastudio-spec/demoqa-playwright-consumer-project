package com.demoqa.playwright.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;
import com.automation.utils.Log;
import com.microsoft.playwright.Locator;

public class PlaywrightResizablePage extends PlaywrightBasePage {

    public PlaywrightPageElement restrictedBox;
    public PlaywrightPageElement resizeHandle;

    public PlaywrightResizablePage() {
        super("PlaywrightResizablePage");
    }

    @Override
    protected void initElements() {
        restrictedBox = register("restrictedBox", "Restricted resizable box", "#resizableBoxWithRestriction");
        resizeHandle = register("resizeHandle", "Resize handle", "#resizableBoxWithRestriction .react-resizable-handle");
    }

    public void validateAndHealPageElements() {
        Log.info("[PLAYWRIGHT] Validating select menu elements");
        super.validateAndHealPageElements();
    }
    
    public void resizeRestrictedBox(int xOffset, int yOffset) {
        Log.info("[PLAYWRIGHT] Resizing restricted box by: " + xOffset + ", " + yOffset);
        scrollIntoView(resizeHandle);
        Locator handle = getPage().locator(resizeHandle.getEffectiveSelector());
        com.microsoft.playwright.options.BoundingBox box = handle.boundingBox();
        if (box != null) {
            getPage().mouse().move(box.x + box.width / 2, box.y + box.height / 2);
            getPage().mouse().down();
            getPage().mouse().move(box.x + box.width / 2 + xOffset, box.y + box.height / 2 + yOffset);
            getPage().mouse().up();
        }
    }

    public double getRestrictedBoxWidth() {
        com.microsoft.playwright.options.BoundingBox box = getPage().locator(restrictedBox.getEffectiveSelector()).boundingBox();
        return box != null ? box.width : 200;
    }

    public double getRestrictedBoxHeight() {
        com.microsoft.playwright.options.BoundingBox box = getPage().locator(restrictedBox.getEffectiveSelector()).boundingBox();
        return box != null ? box.height : 200;
    }
}
