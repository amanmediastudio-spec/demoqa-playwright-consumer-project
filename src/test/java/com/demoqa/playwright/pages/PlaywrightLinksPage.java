package com.demoqa.playwright.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;
import com.automation.utils.Log;
import com.microsoft.playwright.Page;

public class PlaywrightLinksPage extends PlaywrightBasePage {

    public PlaywrightPageElement simpleLink;
    public PlaywrightPageElement dynamicLink;
    public PlaywrightPageElement createdLink;
    public PlaywrightPageElement noContentLink;
    public PlaywrightPageElement movedLink;
    public PlaywrightPageElement badRequestLink;
    public PlaywrightPageElement unauthorizedLink;
    public PlaywrightPageElement forbiddenLink;
    public PlaywrightPageElement invalidUrlLink;
    public PlaywrightPageElement linkResponse;

    private Page childPage;

    public PlaywrightLinksPage() {
        super("PlaywrightLinksPage");
    }

    public void validateAndHealPageElements() {
        Log.info("[PLAYWRIGHT] Validating select menu elements");
        super.validateAndHealPageElements();
    }

    @Override
    protected void initElements() {
        simpleLink = register("simpleLink", "Simple home link", "#simpleLink");
        dynamicLink = register("dynamicLink", "Dynamic home link", "#dynamicLink");
        createdLink = register("createdLink", "API Created link (201)", "#created");
        noContentLink = register("noContentLink", "API No Content link (204)", "#no-content");
        movedLink = register("movedLink", "API Moved link (301)", "#moved");
        badRequestLink = register("badRequestLink", "API Bad Request link (400)", "#bad-request");
        unauthorizedLink = register("unauthorizedLink", "API Unauthorized link (401)", "#unauthorized");
        forbiddenLink = register("forbiddenLink", "API Forbidden link (403)", "#forbidden");
        invalidUrlLink = register("invalidUrlLink", "API Not Found link (404)", "#invalid-url");
        linkResponse = register("linkResponse", "API status link response text container", "#linkResponse");
    }

    public void clickSimpleLinkAndSwitchTab() {
        Log.info("[PLAYWRIGHT] Clicking simple link and waiting for new tab");
        childPage = getPage().waitForPopup(() -> {
            click(simpleLink);
        });
        childPage.waitForLoadState();
    }

    public String getChildPageUrl() {
        if (childPage != null) {
            String url = childPage.url();
            childPage.close();
            childPage = null;
            return url;
        }
        return getPage().url();
    }

    public void clickApiLink(String linkType) {
        Log.info("[PLAYWRIGHT] Clicking API link: " + linkType);
        PlaywrightPageElement target = switch (linkType.toLowerCase()) {
            case "created" -> createdLink;
            case "nocontent", "no content", "no-content" -> noContentLink;
            case "moved" -> movedLink;
            case "badrequest", "bad request", "bad-request" -> badRequestLink;
            case "unauthorized" -> unauthorizedLink;
            case "forbidden" -> forbiddenLink;
            case "notfound", "not found", "invalid-url" -> invalidUrlLink;
            default -> createdLink;
        };
        scrollIntoView(target);
        click(target);
    }

    public String getLinkResponseText() {
        waitForSelector(linkResponse, 5000);
        return getText(linkResponse).trim();
    }
}
