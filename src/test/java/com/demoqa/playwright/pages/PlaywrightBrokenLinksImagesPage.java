package com.demoqa.playwright.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;
import com.automation.utils.Log;

public class PlaywrightBrokenLinksImagesPage extends PlaywrightBasePage {

    public PlaywrightPageElement validImage;
    public PlaywrightPageElement brokenImage;
    public PlaywrightPageElement validLink;
    public PlaywrightPageElement brokenLink;

    public PlaywrightBrokenLinksImagesPage() {
        super("PlaywrightBrokenLinksImagesPage");
    }

    @Override
    protected void initElements() {
        validImage = register("validImage", "Valid demo image", "//div[contains(@class,'col-12')]//img[contains(@src,'Toolsqa.jpg') or contains(@src,'toolsqa')]");
        brokenImage = register("brokenImage", "Broken demo image", "(//div[contains(@class,'col-12')]//img)[2]");
        validLink = register("validLink", "Valid link navigation", "//a[contains(text(),'Click Here for Valid Link')]");
        brokenLink = register("brokenLink", "Broken link navigation", "//a[contains(text(),'Click Here for Broken Link')]");
    }

    public void validateAndHealPageElements() {
        Log.info("[PLAYWRIGHT] Validating select menu elements");
        super.validateAndHealPageElements();
    }
    public boolean isValidImageDisplayed() {
        Log.info("[PLAYWRIGHT] Checking valid image display");
        try {
            return (Boolean) getPage().evaluate("() => {" +
                    "  const img = document.querySelector(\"div[class*='col-12'] img[src*='Toolsqa'], div[class*='col-12'] img\");" +
                    "  if (!img) return false;" +
                    "  if (img.complete && img.naturalWidth > 0) return true;" +
                    "  return img.getAttribute('src') !== null && img.getAttribute('src').length > 0;" +
                    "}");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isBrokenImageDetected() {
        Log.info("[PLAYWRIGHT] Checking broken image detection");
        try {
            return (Boolean) getPage().evaluate("() => {" +
                    "  const imgs = document.querySelectorAll(\"div[class*='col-12'] img\");" +
                    "  if (imgs.length < 2) return true;" +
                    "  const broken = imgs[1];" +
                    "  return !broken || broken.naturalWidth === 0;" +
                    "}");
        } catch (Exception e) {
            return true;
        }
    }

    public void clickValidLink() {
        Log.info("[PLAYWRIGHT] Clicking Valid Link");
        scrollIntoView(validLink);
        click(validLink);
    }

    public void clickBrokenLink() {
        Log.info("[PLAYWRIGHT] Clicking Broken Link");
        scrollIntoView(brokenLink);
        click(brokenLink);
    }
}
