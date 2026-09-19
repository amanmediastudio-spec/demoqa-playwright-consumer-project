package com.demoqa.playwright.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;
import com.automation.utils.Log;

public class PlaywrightRadioButtonPage extends PlaywrightBasePage {

    public PlaywrightPageElement yesRadioLabel;
    public PlaywrightPageElement impressiveRadioLabel;
    public PlaywrightPageElement selectedResultText;

    public PlaywrightRadioButtonPage() {
        super("PlaywrightRadioButtonPage");
    }

    @Override
    protected void initElements() {
        // Diverse native Playwright locator strategies:
        yesRadioLabel = register("yesRadioLabel", "Yes radio button label", "label[for='yesRadio']"); // CSS Attribute
        impressiveRadioLabel = register("impressiveRadioLabel", "Impressive radio button label", "//label[text()='Impressive']"); // XPath Text Match
        selectedResultText = register("selectedResultText", "Selected radio button result text", "span.text-success"); // CSS Class
    }

    public void validateAndHealPageElements() {
        Log.info("[PLAYWRIGHT] Validating select menu elements");
        super.validateAndHealPageElements();
    }

    public void simulateBrokenYesRadio() {
        yesRadioLabel.setBrokenSelectorForSimulation("label[for='broken_yesRadio_label_3333']");
    }

    public void simulateBrokenImpressiveRadio() {
        impressiveRadioLabel.setBrokenSelectorForSimulation("label[for='broken_impressiveRadio_label_2222']");
    }

    public void open() {
        navigate("https://demoqa.com/radio-button");
    }

    public void selectYes() {
        selectYesRadio();
    }

    public void selectYesRadio() {
        click(yesRadioLabel);
    }

    public void selectImpressive() {
        selectImpressiveRadio();
    }

    public void selectImpressiveRadio() {
        click(impressiveRadioLabel);
    }

    public String getSelectedText() {
        return getSelectedResultText();
    }

    public String getSelectedResultText() {
        return getText(selectedResultText);
    }
}
