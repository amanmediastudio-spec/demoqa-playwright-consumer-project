package com.demoqa.playwright.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;
import com.automation.utils.Log;

public class PlaywrightDatePickerPage extends PlaywrightBasePage {

    public PlaywrightPageElement dateInput;
    public PlaywrightPageElement dateTimeInput;

    public PlaywrightDatePickerPage() {
        super("PlaywrightDatePickerPage");
    }

    @Override
    protected void initElements() {
        dateInput = register("dateInput", "Select Date input", "#datePickerMonthYearInput");
        dateTimeInput = register("dateTimeInput", "Date and Time input", "#dateAndTimePickerInput");
    }

    public void validateAndHealPageElements() {
        Log.info("[PLAYWRIGHT] Validating select menu elements");
        super.validateAndHealPageElements();
    }

    public void setSelectDate(String dateValue) {
        Log.info("[PLAYWRIGHT] Setting Select Date: " + dateValue);
        click(dateInput);
        getPage().keyboard().press("Control+A");
        getPage().keyboard().press("Backspace");
        fill(dateInput, dateValue);
        getPage().keyboard().press("Enter");
    }

    public String getSelectedDate() {
        return inputValue(dateInput);
    }

    public void setDateTime(String dateTimeValue) {
        Log.info("[PLAYWRIGHT] Setting Date and Time: " + dateTimeValue);
        click(dateTimeInput);
        getPage().keyboard().press("Control+A");
        getPage().keyboard().press("Backspace");
        fill(dateTimeInput, dateTimeValue);
        getPage().keyboard().press("Enter");
    }

    public String getSelectedDateTime() {
        return inputValue(dateTimeInput);
    }
}
