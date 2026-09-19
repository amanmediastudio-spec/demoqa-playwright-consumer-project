package com.demoqa.playwright.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;
import com.automation.utils.Log;

public class PlaywrightSliderPage extends PlaywrightBasePage {

    public PlaywrightPageElement sliderInput;
    public PlaywrightPageElement sliderValue;

    public PlaywrightSliderPage() {
        super("PlaywrightSliderPage");
    }

    @Override
    protected void initElements() {
        sliderInput = register("sliderInput", "Range slider input", ".range-slider, input[type='range']");
        sliderValue = register("sliderValue", "Slider value text field", "#sliderValue");
    }

    public void validateAndHealPageElements() {
        Log.info("[PLAYWRIGHT] Validating select menu elements");
        super.validateAndHealPageElements();
    }

    public void setSliderValue(int value) {
        Log.info("[PLAYWRIGHT] Setting slider value to: " + value);
        getPage().evaluate("val => {" +
                "  const input = document.querySelector('input.range-slider, input[type=\"range\"]');" +
                "  if (input) {" +
                "    const setter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
                "    setter.call(input, val);" +
                "    input.dispatchEvent(new Event('input', { bubbles: true }));" +
                "    input.dispatchEvent(new Event('change', { bubbles: true }));" +
                "  }" +
                "  const valBox = document.querySelector('#sliderValue');" +
                "  if (valBox) {" +
                "    const setter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
                "    setter.call(valBox, val);" +
                "    valBox.dispatchEvent(new Event('input', { bubbles: true }));" +
                "    valBox.dispatchEvent(new Event('change', { bubbles: true }));" +
                "  }" +
                "}", value);
        getPage().waitForTimeout(200);
    }

    public int getSliderValue() {
        String val = inputValue(sliderValue);
        return Integer.parseInt(val.trim());
    }
}
