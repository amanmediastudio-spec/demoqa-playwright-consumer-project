package com.demoqa.playwright.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;
import com.automation.utils.Log;

public class PlaywrightTabsPage extends PlaywrightBasePage {

    public PlaywrightPageElement tabWhat;
    public PlaywrightPageElement tabOrigin;
    public PlaywrightPageElement tabUse;
    public PlaywrightPageElement tabMore;
    public PlaywrightPageElement tabpaneWhat;
    public PlaywrightPageElement tabpaneOrigin;
    public PlaywrightPageElement tabpaneUse;

    public PlaywrightTabsPage() {
        super("PlaywrightTabsPage");
    }

    @Override
    protected void initElements() {
        tabWhat = register("tabWhat", "What tab button", "#demo-tab-what");
        tabOrigin = register("tabOrigin", "Origin tab button", "#demo-tab-origin");
        tabUse = register("tabUse", "Use tab button", "#demo-tab-use");
        tabMore = register("tabMore", "More disabled tab button", "#demo-tab-more");
        tabpaneWhat = register("tabpaneWhat", "What tab pane content", "#demo-tabpane-what");
        tabpaneOrigin = register("tabpaneOrigin", "Origin tab pane content", "#demo-tabpane-origin");
        tabpaneUse = register("tabpaneUse", "Use tab pane content", "#demo-tabpane-use");
    }

    public void validateAndHealPageElements() {
        Log.info("[PLAYWRIGHT] Validating select menu elements");
        super.validateAndHealPageElements();
    }

    private PlaywrightPageElement getTab(String tabName) {
        String key = tabName.toLowerCase();
        return switch (key) {
            case "what" -> tabWhat;
            case "origin" -> tabOrigin;
            case "use" -> tabUse;
            case "more" -> tabMore;
            default -> register("tab_" + key, "Tab " + tabName, "#demo-tab-" + key);
        };
    }

    private PlaywrightPageElement getTabPane(String tabName) {
        String key = tabName.toLowerCase();
        return switch (key) {
            case "what" -> tabpaneWhat;
            case "origin" -> tabpaneOrigin;
            case "use" -> tabpaneUse;
            default -> register("tabpane_" + key, "Tab pane " + tabName, "#demo-tabpane-" + key);
        };
    }

    public void clickTab(String tabName) {
        Log.info("[PLAYWRIGHT] Clicking tab: " + tabName);
        click(getTab(tabName));
        getPage().waitForTimeout(500);
    }

    public boolean isTabActive(String tabName) {
        String aria = getAttribute(getTab(tabName), "aria-selected");
        return "true".equalsIgnoreCase(aria);
    }

    public String getTabContentText(String tabName) {
        return getText(getTabPane(tabName)).trim();
    }

    public String getActiveTabPaneContent() {
        return (String) getPage().evaluate("() => {" +
                "  const active = document.querySelector('.tab-pane.active, .tab-content > .active');" +
                "  return active ? active.innerText.trim() : '';" +
                "}");
    }

    public boolean isMoreTabDisabled() {
        String classes = getAttribute(tabMore, "class");
        String aria = getAttribute(tabMore, "aria-disabled");
        return (classes != null && classes.contains("disabled")) || "true".equalsIgnoreCase(aria);
    }
}
