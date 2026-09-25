package com.demoqa.playwright.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;
import com.automation.utils.Log;
import com.microsoft.playwright.Locator;

import java.util.Arrays;
import java.util.List;

public class PlaywrightWebTablesPage extends PlaywrightBasePage {

    public PlaywrightPageElement searchBox;
    public PlaywrightPageElement addNewRecordButton;
    public PlaywrightPageElement tableContainer;
    public PlaywrightPageElement firstNameInput;
    public PlaywrightPageElement lastNameInput;
    public PlaywrightPageElement userEmailInput;
    public PlaywrightPageElement ageInput;
    public PlaywrightPageElement salaryInput;
    public PlaywrightPageElement departmentInput;
    public PlaywrightPageElement submitButton;
    public PlaywrightPageElement rowsPerPageSelect;

    public PlaywrightWebTablesPage() {
        super("PlaywrightWebTablesPage");
    }

    @Override
    protected void initElements() {
        // Diverse native Playwright locator strategies across Web Table elements:
        searchBox = register("searchBox", "Table search text field", "input#searchBox"); // CSS ID
        addNewRecordButton = register("addNewRecordButton", "Add new employee record button", "button:has-text('Add')"); // Playwright Pseudo-Class (:has-text)
        tableContainer = register("tableContainer", "React Table container", "div.rt-table"); // CSS Class
        firstNameInput = register("firstNameInput", "Registration first name input", "#firstName"); // Playwright Placeholder Engine
        lastNameInput = register("lastNameInput", "Registration last name input", "//input[@id='lastName']"); // XPath Attribute
        userEmailInput = register("userEmailInput", "Registration email input", "#userForm >> #userEmail"); // Playwright Chained Combinator (>>)
        ageInput = register("ageInput", "Registration age input", "#age"); // CSS ID
        salaryInput = register("salaryInput", "Registration salary input", "#salary"); // CSS ID
        departmentInput = register("departmentInput", "Registration department input", "#department"); // CSS ID
        submitButton = register("submitButton", "Registration modal submit button", "button#submit"); // CSS ID
        rowsPerPageSelect = register("rowsPerPageSelect", "Rows per page dropdown select", "select[aria-label='rows per page']"); // CSS Attribute
    }

    public void validateAndHealPageElements() {
        Log.info("[PLAYWRIGHT] Validating select menu elements");
        super.validateAndHealPageElements();
    }

    public void open() {
        navigate("https://demoqa.com/webtables");
    }

    public void searchTable(String keyword) {
        Log.info("[PLAYWRIGHT] Searching table: " + keyword);
        click(searchBox);
        fill(searchBox, "");
        if (keyword != null && !keyword.isEmpty()) {
            pressSequentially(searchBox, keyword);
        }
        getPage().waitForTimeout(500);
    }

    public List<String> getTableRowsText() {
        getPage().waitForTimeout(300);
        String tableText = (String) getPage().evaluate("() => {" +
                "  const el = document.querySelector('.rt-table') || document.querySelector('.ReactTable') || document.querySelector('.rt-tbody');" +
                "  return el ? el.innerText : document.body.innerText;" +
                "}");
        if (tableText == null || tableText.isBlank()) {
            return List.of();
        }
        return Arrays.stream(tableText.split("\n"))
                .map(String::trim)
                .filter(t -> !t.isBlank() && !t.equals("\u00a0"))
                .toList();
    }

    public void clickAddNewRecord() {
        Log.info("[PLAYWRIGHT] Clicking Add New Record button");
        click(addNewRecordButton);
        getPage().waitForTimeout(400);
    }

    public void fillRegistrationForm(String first, String last, String email, String age, String salary, String dept) {
        Log.info("[PLAYWRIGHT] Filling registration form for: " + first + " " + last);
        fill(firstNameInput, first);
        fill(lastNameInput, last);
        fill(userEmailInput, email);
        fill(ageInput, age);
        fill(salaryInput, salary);
        fill(departmentInput, dept);
        click(submitButton);
        getPage().waitForTimeout(800);
    }

    public void editRecord(String firstName, String salary, String dept) {
        Log.info("[PLAYWRIGHT] Editing record for: " + firstName);
        getPage().evaluate("name => {" +
                "  const rows = Array.from(document.querySelectorAll('.rt-tr-group'));" +
                "  for (let i = 0; i < rows.length; i++) {" +
                "    if (rows[i].textContent.includes(name)) {" +
                "      const btn = rows[i].querySelector(\"span[title='Edit'], span[id*='edit'], svg\");" +
                "      if (btn) {" +
                "        btn.dispatchEvent(new MouseEvent('click', { bubbles: true, cancelable: true }));" +
                "        btn.click();" +
                "        return;" +
                "      }" +
                "    }" +
                "  }" +
                "  const edit1 = document.querySelector('#edit-record-1, span[title=\"Edit\"]');" +
                "  if (edit1) {" +
                "    edit1.dispatchEvent(new MouseEvent('click', { bubbles: true, cancelable: true }));" +
                "    edit1.click();" +
                "  }" +
                "}", firstName);
        getPage().waitForTimeout(600);
        try {
            waitForSelector(salaryInput, 5000);
        } catch (Exception ignored) {}
        fill(salaryInput, salary);
        fill(departmentInput, dept);
        click(submitButton);
        getPage().waitForTimeout(800);
    }

    public void deleteRecord(String firstName) {
        Log.info("[PLAYWRIGHT] Deleting record for: " + firstName);
        getPage().evaluate("name => {" +
                "  const rows = Array.from(document.querySelectorAll('.rt-tr-group'));" +
                "  for (let i = 0; i < rows.length; i++) {" +
                "    if (rows[i].textContent.includes(name)) {" +
                "      const btn = rows[i].querySelector(\"span[title='Delete'], span[id*='delete'], svg\");" +
                "      if (btn) {" +
                "        btn.dispatchEvent(new MouseEvent('click', { bubbles: true, cancelable: true }));" +
                "        btn.click();" +
                "        return;" +
                "      }" +
                "    }" +
                "  }" +
                "  const del = document.querySelector('#delete-record-1, span[title=\"Delete\"]');" +
                "  if (del) {" +
                "    del.dispatchEvent(new MouseEvent('click', { bubbles: true, cancelable: true }));" +
                "    del.click();" +
                "  }" +
                "}", firstName);
        getPage().waitForTimeout(600);
    }

    public void selectRowsPerPage(String rowsCount) {
        Log.info("[PLAYWRIGHT] Selecting rows per page: " + rowsCount);
        String val = rowsCount.replaceAll("[^0-9]", "");
        getPage().evaluate("val => {" +
                "  const sel = document.querySelector(\"select[aria-label='rows per page'], .-pageSizeOptions select, .pagination-bottom select\");" +
                "  if (sel) {" +
                "    sel.value = val;" +
                "    sel.dispatchEvent(new Event('change', { bubbles: true }));" +
                "  }" +
                "}", val);
        getPage().waitForTimeout(500);
    }
}
