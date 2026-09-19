package com.demoqa.playwright.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;
import com.automation.utils.Log;

import java.util.List;

public class PlaywrightSelectMenuPage extends PlaywrightBasePage {

    public PlaywrightPageElement withOptGroupDropdown;
    public PlaywrightPageElement selectOneDropdown;
    public PlaywrightPageElement oldSelectMenu;
    public PlaywrightPageElement multiselectInput;
    public PlaywrightPageElement standardCarsSelect;
    public PlaywrightPageElement selectedGroupOptionText;
    public PlaywrightPageElement selectedTitleOneText;

    public PlaywrightSelectMenuPage() {
        super("PlaywrightSelectMenuPage");
    }

    @Override
    protected void initElements() {
        withOptGroupDropdown = register("withOptGroupDropdown", "Select with opt group container", "#withOptGroup");
        selectOneDropdown = register("selectOneDropdown", "Select one title container", "#selectMenuContainer #selectOne");
        oldSelectMenu = register("oldSelectMenu", "Old style classic select dropdown", "#oldSelectMenu");
        multiselectInput = register("multiselectInput", "Multiselect tag input", "#react-select-4-input");
        standardCarsSelect = register("standardCarsSelect", "Standard multiselect cars", "#cars");
        selectedGroupOptionText = register("selectedGroupOptionText", "Selected group option value", "#withOptGroup .css-1uccc91-singleValue, #withOptGroup div[class*='singleValue']");
        selectedTitleOneText = register("selectedTitleOneText", "Selected title one value", "#selectOne .css-1uccc91-singleValue, #selectOne div[class*='singleValue']");
    }

    public void open() {
        navigate("https://demoqa.com/select-menu");
    }

    public void validateAndHealPageElements() {
        Log.info("[PLAYWRIGHT] Validating select menu elements");
    }

    public void selectGroupOption(String option) {
        Log.info("[PLAYWRIGHT] Selecting group option: " + option);
        click(withOptGroupDropdown);
        PlaywrightPageElement opt = register("groupOption_" + option, "Group option: " + option, "//div[contains(@id,'react-select-2-option') and text()='" + option + "']");
        click(opt);
    }

    public String getSelectedGroupOptionText() {
        return getText(selectedGroupOptionText).trim();
    }

    public void selectTitleOne(String title) {
        Log.info("[PLAYWRIGHT] Selecting title one: " + title);
        click(selectOneDropdown);
        PlaywrightPageElement opt = register("titleOption_" + title, "Title option: " + title, "//div[contains(@id,'react-select-3-option') and text()='" + title + "']");
        click(opt);
    }

    public String getSelectedTitleOneText() {
        return getText(selectedTitleOneText).trim();
    }

    public void selectOldOption(String label) {
        selectOldStyleOption(label);
    }

    public void selectOldStyleOption(String color) {
        Log.info("[PLAYWRIGHT] Selecting old style color: " + color);
        selectOption(oldSelectMenu, color);
    }

    public String getSelectedOldOption() {
        return getSelectedOldStyleOption();
    }

    public String getSelectedOldStyleOption() {
        return getPage().locator("#oldSelectMenu option:checked").innerText().trim();
    }

    public void selectMultiColorOptions(List<String> colors) {
        Log.info("[PLAYWRIGHT] Selecting multi color options: " + colors);
        for (String color : colors) {
            click(multiselectInput);
            pressSequentially(multiselectInput, color);
            getPage().waitForTimeout(300);
            getPage().keyboard().press("Enter");
            getPage().waitForTimeout(300);
        }
        getPage().locator("body").click();
    }

    public List<String> getSelectedMultiColorOptions() {
        try {
            return getPage().locator(".css-12jo7m5, div[class*='multi-value__label'], div[class*='multiValue']").allInnerTexts();
        } catch (Exception e) {
            return List.of();
        }
    }

    public void selectStandardCars(List<String> cars) {
        Log.info("[PLAYWRIGHT] Selecting standard cars: " + cars);
        String[] carsArray = cars.toArray(new String[0]);
        getPage().locator("#cars").selectOption(carsArray);
    }

    public List<String> getSelectedStandardCars() {
        return getPage().locator("#cars option:checked").allInnerTexts();
    }
}
