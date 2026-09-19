package com.demoqa.playwright.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;
import com.automation.utils.Log;

import java.util.List;

public class PlaywrightSortablePage extends PlaywrightBasePage {

    public PlaywrightPageElement listTab;
    public PlaywrightPageElement gridTab;
    public PlaywrightPageElement listContainer;

    public PlaywrightSortablePage() {
        super("PlaywrightSortablePage");
    }

    @Override
    protected void initElements() {
        listTab = register("listTab", "List tab", "#demo-tab-list");
        gridTab = register("gridTab", "Grid tab", "#demo-tab-grid");
        listContainer = register("listContainer", "List sortable container", "#demo-tabpane-list");
    }

    public void validateAndHealPageElements() {
        Log.info("[PLAYWRIGHT] Validating select menu elements");
        super.validateAndHealPageElements();
    }

    public void dragListItemToTarget(String sourceText, String targetText) {
        Log.info("[PLAYWRIGHT] Dragging sortable item '" + sourceText + "' to '" + targetText + "'");
        PlaywrightPageElement src = register("sortable_src_" + sourceText, "Sortable source: " + sourceText, "//div[@id='demo-tabpane-list']//div[contains(@class,'list-group-item') and text()='" + sourceText + "']");
        PlaywrightPageElement tgt = register("sortable_tgt_" + targetText, "Sortable target: " + targetText, "//div[@id='demo-tabpane-list']//div[contains(@class,'list-group-item') and text()='" + targetText + "']");
        getPage().locator(src.getEffectiveSelector()).dragTo(getPage().locator(tgt.getEffectiveSelector()));
    }

    public List<String> getListItemsText() {
        return getPage().locator("#demo-tabpane-list .list-group-item").allInnerTexts();
    }
}
