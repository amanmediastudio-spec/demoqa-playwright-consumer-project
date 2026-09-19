package com.demoqa.playwright.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;
import com.automation.utils.Log;

public class PlaywrightMenuPage extends PlaywrightBasePage {

    public PlaywrightPageElement mainItem2;
    public PlaywrightPageElement subSubList;
    public PlaywrightPageElement subSubItem1;

    public PlaywrightMenuPage() {
        super("PlaywrightMenuPage");
    }

    @Override
    protected void initElements() {
        mainItem2 = register("mainItem2", "Main menu item 2", "//a[text()='Main Item 2']");
        subSubList = register("subSubList", "Sub sub list menu option", "//a[text()='SUB SUB LIST »']");
        subSubItem1 = register("subSubItem1", "Sub sub item 1 link", "//a[text()='Sub Sub Item 1']");
    }

    public void validateAndHealPageElements() {
        Log.info("[PLAYWRIGHT] Validating select menu elements");
        super.validateAndHealPageElements();
    }

    public void hoverMenuHierarchy() {
        Log.info("[PLAYWRIGHT] Hovering Main Item 2 -> SUB SUB LIST -> Sub Sub Item 1");
        hover(mainItem2);
        hover(subSubList);
    }

    public boolean isSubSubItemDisplayed() {
        return isVisible(subSubItem1);
    }
}
