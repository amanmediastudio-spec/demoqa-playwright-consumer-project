package com.demoqa.playwright.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;
import com.automation.utils.Log;

import java.io.File;
import java.util.List;

public class PlaywrightPracticeFormPage extends PlaywrightBasePage {

    public PlaywrightPageElement firstNameInput;
    public PlaywrightPageElement lastNameInput;
    public PlaywrightPageElement userEmailInput;
    public PlaywrightPageElement userNumberInput;
    public PlaywrightPageElement dateOfBirthInput;
    public PlaywrightPageElement dobYearSelect;
    public PlaywrightPageElement dobMonthSelect;
    public PlaywrightPageElement subjectsInput;
    public PlaywrightPageElement uploadPictureInput;
    public PlaywrightPageElement currentAddressInput;
    public PlaywrightPageElement stateDropdown;
    public PlaywrightPageElement cityDropdown;
    public PlaywrightPageElement submitButton;
    public PlaywrightPageElement modalTitle;
    public PlaywrightPageElement modalTable;
    public PlaywrightPageElement closeModalButton;

    public PlaywrightPracticeFormPage() {
        super("PlaywrightPracticeFormPage");
    }

    @Override
    protected void initElements() {
        // Diverse native Playwright locator strategies:
        firstNameInput = register("firstNameInput", "First name text input", "input#firstName"); // CSS ID
        lastNameInput = register("lastNameInput", "Last name text input", "#lastName"); // Playwright Placeholder Engine
        userEmailInput = register("userEmailInput", "User email text input", "//input[@id='userEmail']"); // XPath Attribute
        userNumberInput = register("userNumberInput", "Mobile number text input", "input#userNumber"); // CSS ID
        dateOfBirthInput = register("dateOfBirthInput", "Date of birth input field", "#dateOfBirthInput"); // CSS ID
        dobYearSelect = register("dobYearSelect", "Date of birth year select", "select.react-datepicker__year-select"); // CSS Class
        dobMonthSelect = register("dobMonthSelect", "Date of birth month select", "select.react-datepicker__month-select"); // CSS Class
        subjectsInput = register("subjectsInput", "Subjects autocomplete input", "#subjectsInput"); // CSS ID
        uploadPictureInput = register("uploadPictureInput", "Upload picture input", "input#uploadPicture"); // CSS Tag + ID
        currentAddressInput = register("currentAddressInput", "Current address text area", "textarea#currentAddress"); // CSS Tag + ID
        stateDropdown = register("stateDropdown", "State select container", "div#state"); // CSS ID
        cityDropdown = register("cityDropdown", "City select container", "div#city"); // CSS ID
        submitButton = register("submitButton", "Submit registration form button", "button:has-text('Submit')"); // Playwright Pseudo-Class (:has-text)
        modalTitle = register("modalTitle", "Submission confirmation modal title", "#example-modal-sizes-title-lg"); // CSS ID
        modalTable = register("modalTable", "Submission confirmation table", "div.table-responsive"); // CSS Class
        closeModalButton = register("closeModalButton", "Close confirmation modal button", "button#closeLargeModal"); // CSS ID
    }

    public void validateAndHealPageElements() {
        Log.info("[PLAYWRIGHT] Validating select menu elements");
        super.validateAndHealPageElements();
    }

    public void open() {
        navigate("https://demoqa.com/automation-practice-form");
    }

    public void fillPersonalDetails(String first, String last, String email, String gender, String mobile) {
        Log.info("[PLAYWRIGHT] Filling personal details for: " + first + " " + last);
        fill(firstNameInput, first);
        fill(lastNameInput, last);
        fill(userEmailInput, email);

        // Gender radio label
        PlaywrightPageElement genderRadio = register("genderRadio_" + gender, "Gender radio option: " + gender, "//label[text()='" + gender + "']");
        click(genderRadio);

        fill(userNumberInput, mobile);
    }

    public void setDateOfBirth(String day, String month, String year) {
        Log.info("[PLAYWRIGHT] Setting Date of Birth: " + day + " " + month + " " + year);
        click(dateOfBirthInput);
        selectOption(dobYearSelect, year);
        selectOption(dobMonthSelect, month);

        int d = Integer.parseInt(day.replaceAll("\\D", ""));
        String daySelector = String.format(".react-datepicker__day--%03d:not(.react-datepicker__day--outside-month)", d);
        PlaywrightPageElement dayElement = register("dobDayPicker_" + d, "Date of birth day: " + d, daySelector);
        click(dayElement);
    }

    public void addSubjects(List<String> subjects) {
        Log.info("[PLAYWRIGHT] Adding subjects: " + subjects);
        for (String s : subjects) {
            fill(subjectsInput, s);
            getPage().waitForTimeout(200);
            press(subjectsInput, "Enter");
            getPage().waitForTimeout(200);
        }
    }

    public void selectHobbies(List<String> hobbies) {
        Log.info("[PLAYWRIGHT] Selecting hobbies: " + hobbies);
        for (String h : hobbies) {
            PlaywrightPageElement hobbyElem = register("hobby_" + h, "Hobby checkbox: " + h, "//label[text()='" + h + "']");
            click(hobbyElem);
        }
    }

    public void uploadPicture(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            Log.info("[PLAYWRIGHT] Uploading picture: " + file.getAbsolutePath());
            setInputFiles(uploadPictureInput, file.toPath());
        }
    }

    public void fillAddressAndStateCity(String currentAddress, String state, String city) {
        Log.info("[PLAYWRIGHT] Filling address and state/city");
        fill(currentAddressInput, currentAddress);

        // State select
        scrollIntoView(stateDropdown);
        click(stateDropdown);
        PlaywrightPageElement stateOption = register("stateOption_" + state, "State option: " + state, "//div[contains(@id,'react-select') and text()='" + state + "']");
        click(stateOption);

        // City select
        scrollIntoView(cityDropdown);
        click(cityDropdown);
        PlaywrightPageElement cityOption = register("cityOption_" + city, "City option: " + city, "//div[contains(@id,'react-select') and text()='" + city + "']");
        click(cityOption);
    }

    public void submitForm() {
        Log.info("[PLAYWRIGHT] Submitting practice form");
        scrollIntoView(submitButton);
        click(submitButton);
        getPage().waitForTimeout(600);
    }

    public String getModalTitle() {
        waitForSelector(modalTitle, 10000);
        return getText(modalTitle).trim();
    }

    public String getModalTableData() {
        return getText(modalTable);
    }

    public void closeModal() {
        if (isVisible(closeModalButton)) {
            click(closeModalButton);
        }
    }
}
