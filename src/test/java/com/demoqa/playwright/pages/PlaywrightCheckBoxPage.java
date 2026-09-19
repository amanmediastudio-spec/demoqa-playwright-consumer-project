package com.demoqa.playwright.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;
import com.automation.utils.Log;

public class PlaywrightCheckBoxPage extends PlaywrightBasePage {

    public PlaywrightPageElement expandAllBtn;
    public PlaywrightPageElement collapseAllBtn;
    public PlaywrightPageElement homeCheckbox;
    public PlaywrightPageElement resultPanel;

    public PlaywrightCheckBoxPage() {
        super("PlaywrightCheckBoxPage");
    }

    @Override
    protected void initElements() {
        // Diverse native Playwright locator strategies:
        expandAllBtn = register("expandAllBtn", "Expand all tree nodes button", "title=\"Expand all\""); // Playwright Title Engine
        collapseAllBtn = register("collapseAllBtn", "Collapse all tree nodes button", "title=\"Collapse all\""); // Playwright Title Engine
        homeCheckbox = register("homeCheckbox", "Home root checkbox label", "label[for='tree-node-home'] >> .rct-checkbox"); // Playwright Chained Combinator (>>)
        resultPanel = register("resultPanel", "Selected checkboxes result list", "#result"); // CSS ID
    }

    public void validateAndHealPageElements() {
        Log.info("[PLAYWRIGHT] Validating select menu elements");
        super.validateAndHealPageElements();
    }

    public void open() {
        navigate("https://demoqa.com/checkbox");
    }

    public void expandAll() {
        expandAllNodes();
    }

    public void expandAllNodes() {
        Log.info("[PLAYWRIGHT] Expanding all checkbox tree nodes");
        try {
            click(expandAllBtn);
        } catch (Exception e) {
            // fallback via JS click if button is hidden or different selector
            getPage().evaluate("() => {" +
                    "  const btn = document.querySelector(\"button[title='Expand all'], button.rct-option-expand-all, button[aria-label='Expand all']\");" +
                    "  if (btn) btn.click();" +
                    "}");
        }
        getPage().evaluate("() => {" +
                "  for (let i = 0; i < 6; i++) {" +
                "    const closed = document.querySelectorAll('.rc-tree-switcher_close, .rct-node-collapsed .rct-collapse');" +
                "    if (!closed.length) break;" +
                "    closed.forEach(el => el.click());" +
                "  }" +
                "}");
        getPage().waitForTimeout(400);
    }

    public void collapseAllNodes() {
        Log.info("[PLAYWRIGHT] Collapsing all checkbox tree nodes");
        click(collapseAllBtn);
        getPage().evaluate("() => {" +
                "  const open = document.querySelectorAll('.rc-tree-switcher_open, .rct-node-expanded .rct-collapse');" +
                "  open.forEach(el => el.click());" +
                "}");
        getPage().waitForTimeout(300);
    }

    public void toggleNode(String nodeName) {
        Log.info("[PLAYWRIGHT] Toggling checkbox node: " + nodeName);
        getPage().evaluate("name => {" +
                "  const lower = name.trim().toLowerCase();" +
                "  const titles = Array.from(document.querySelectorAll('.rc-tree-title, .rct-title, span'));" +
                "  const target = titles.find(t => t.textContent.trim().toLowerCase() === lower);" +
                "  if (target) {" +
                "    const node = target.closest('.rc-tree-treenode, .rct-node, label, li') || target.parentElement;" +
                "    const cb = node ? (node.querySelector('.rc-tree-checkbox, .rc-tree-checkbox-inner, .rct-checkbox') || node) : target;" +
                "    cb.click();" +
                "    return;" +
                "  }" +
                "  const labelOrInput = document.querySelector(\"label[for*='\" + lower + \"'], input[id*='\" + lower + \"']\");" +
                "  if (labelOrInput) labelOrInput.click();" +
                "}", nodeName);
        getPage().waitForTimeout(400);
    }

    public void selectHomeCheckbox() {
        click(homeCheckbox);
    }

    public String getResultText() {
        try {
            waitForSelector(resultPanel, 5000);
            return getText(resultPanel);
        } catch (Exception e) {
            return (String) getPage().evaluate("() => document.querySelector('#result, .display-result')?.innerText || ''");
        }
    }
}
