package com.demoqa.playwright.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;
import com.automation.utils.Log;

import java.util.List;

public class PlaywrightBookStorePage extends PlaywrightBasePage {

    public PlaywrightPageElement searchBox;
    public PlaywrightPageElement backToStoreBtn;

    public PlaywrightBookStorePage() {
        super("PlaywrightBookStorePage");
    }

    public void validateAndHealPageElements() {
        Log.info("[PLAYWRIGHT] Validating select menu elements");
        super.validateAndHealPageElements();
    }

    @Override
    protected void initElements() {
        searchBox = register("searchBox", "Book Store search box input", "#searchBox");
        backToStoreBtn = register("backToStoreBtn", "Back To Book Store navigation button", "#addNewRecordButton");
    }

    public void searchBook(String query) {
        Log.info("[PLAYWRIGHT] Searching for book: " + query);
        getPage().waitForTimeout(500);
        click(searchBox);
        getPage().keyboard().press("Control+A");
        getPage().keyboard().press("Backspace");
        pressSequentially(searchBox, query);
        getPage().waitForTimeout(1000);
    }

    public List<String> getBookTitles() {
        try {
            getPage().waitForSelector("span[id*='see-book-'] a, .rt-tbody .rt-tr-group a",
                    new com.microsoft.playwright.Page.WaitForSelectorOptions().setTimeout(5000));
            return getPage().locator("span[id*='see-book-'] a, .rt-tbody .rt-tr-group a").allInnerTexts();
        } catch (Exception e) {
            Log.warn("[PLAYWRIGHT] Error getting book titles: " + e.getMessage());
            return List.of();
        }
    }

    public void clickBookByTitle(String title) {
        Log.info("[PLAYWRIGHT] Clicking book by title: " + title);
        PlaywrightPageElement bookLink = register("bookLink_" + title, "Book link for " + title, "//a[contains(text(),'" + title + "')]");
        click(bookLink);
    }

    public String getBookDetailValue(String fieldName) {
        String selector = switch (fieldName.toLowerCase()) {
            case "title" -> "#title-wrapper #userName-value";
            case "author" -> "#author-wrapper #userName-value";
            case "publisher" -> "#publisher-wrapper #userName-value";
            case "isbn" -> "#ISBN-wrapper #userName-value";
            default -> "#" + fieldName.toLowerCase() + "-wrapper #userName-value";
        };
        PlaywrightPageElement fieldElem = register("bookDetail_" + fieldName, "Book detail " + fieldName, selector);
        waitForSelector(fieldElem, 5000);
        return getText(fieldElem).trim();
    }

    public void clickBackToBookStore() {
        Log.info("[PLAYWRIGHT] Navigating back to Book Store");
        click(backToStoreBtn);
    }
}
