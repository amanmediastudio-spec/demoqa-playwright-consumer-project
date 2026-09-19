package com.demoqa.playwright.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;
import com.automation.utils.Log;

public class PlaywrightSelectablePage extends PlaywrightBasePage {

    public PlaywrightPageElement listTab;
    public PlaywrightPageElement gridTab;
    public PlaywrightPageElement verticalListContainer;
    public PlaywrightPageElement gridContainer;

    public PlaywrightSelectablePage() {
        super("PlaywrightSelectablePage");
    }

    @Override
    protected void initElements() {
        listTab = register("listTab", "List tab button", "#demo-tab-list");
        gridTab = register("gridTab", "Grid tab button", "#demo-tab-grid");
        verticalListContainer = register("verticalListContainer", "Vertical list container", "#verticalListContainer");
        gridContainer = register("gridContainer", "Grid container", "#gridContainer");
    }

    public void validateAndHealPageElements() {
        Log.info("[PLAYWRIGHT] Validating select menu elements");
        super.validateAndHealPageElements();
    }

    public void clickListItem(String itemText) {
        Log.info("[PLAYWRIGHT] Clicking list item: " + itemText);
        PlaywrightPageElement item = register("listItem_" + itemText, "List item: " + itemText, "//ul[@id='verticalListContainer']/li[text()='" + itemText + "']");
        click(item);
    }

    public boolean isListItemSelected(String itemText) {
        PlaywrightPageElement item = register("listItem_" + itemText, "List item: " + itemText, "//ul[@id='verticalListContainer']/li[text()='" + itemText + "']");
        String classes = getAttribute(item, "class");
        return classes != null && classes.contains("active");
    }

    public void selectTab(String tabName) {
        Log.info("[PLAYWRIGHT] Switching to selectable tab: " + tabName);
        PlaywrightPageElement tab = "grid".equalsIgnoreCase(tabName) ? gridTab : listTab;
        click(tab);
    }

    public void clickGridItem(String itemText) {
        Log.info("[PLAYWRIGHT] Clicking grid item: " + itemText);
        PlaywrightPageElement item = register("gridItem_" + itemText, "Grid item: " + itemText, "//div[@id='gridContainer']//li[text()='" + itemText + "']");
        click(item);
    }

    public boolean isGridItemSelected(String itemText) {
        PlaywrightPageElement item = register("gridItem_" + itemText, "Grid item: " + itemText, "//div[@id='gridContainer']//li[text()='" + itemText + "']");
        String classes = getAttribute(item, "class");
        return classes != null && classes.contains("active");
    }
}
