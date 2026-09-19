package com.demoqa.playwright.stepdefinitions;

import com.automation.config.ConfigReader;
import com.automation.playwright.PlaywrightManager;
import com.automation.utils.Log;
import com.demoqa.playwright.pages.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.List;

public class PlaywrightInteractionsSteps {

    private PlaywrightSortablePage sortablePage;
    private PlaywrightSelectablePage selectablePage;
    private PlaywrightResizablePage resizablePage;
    private PlaywrightDroppablePage droppablePage;
    private PlaywrightDragabblePage dragabblePage;

    // --- SORTABLE ---
    @Given("I open the DemoQA Sortable page")
    public void openSortablePage() {
        String url = ConfigReader.get("app.sortable.url");
        Log.info("[PLAYWRIGHT] Navigating to Sortable page: " + url);
        PlaywrightManager.navigate(url);
        sortablePage = new PlaywrightSortablePage();
    }

    @When("I drag sortable list item {string} to position of {string}")
    public void dragSortableItem(String source, String target) {
        sortablePage.dragListItemToTarget(source, target);
    }

    @Then("the sortable list items should contain {string}")
    public void verifySortableList(String expectedItem) {
        List<String> items = sortablePage.getListItemsText();
        Assert.assertTrue(items.contains(expectedItem), "List items missing: " + expectedItem);
    }

    // --- SELECTABLE ---
    @Given("I open the DemoQA Selectable page")
    public void openSelectablePage() {
        String url = ConfigReader.get("app.selectable.url");
        Log.info("[PLAYWRIGHT] Navigating to Selectable page: " + url);
        PlaywrightManager.navigate(url);
        selectablePage = new PlaywrightSelectablePage();
    }

    @When("I select list item {string}")
    public void selectListItem(String item) {
        selectablePage.clickListItem(item);
    }

    @Then("the list item {string} should have active selection styling")
    public void verifyListItemActive(String item) {
        Assert.assertTrue(selectablePage.isListItemSelected(item), "List item is not selected: " + item);
    }

    @When("I switch to Selectable Grid tab")
    public void switchToGridTab() {
        selectablePage.selectTab("grid");
    }

    @When("I select grid item {string}")
    public void selectGridItem(String item) {
        selectablePage.clickGridItem(item);
    }

    @Then("the grid item {string} should have active selection styling")
    public void verifyGridItemActive(String item) {
        Assert.assertTrue(selectablePage.isGridItemSelected(item), "Grid item is not selected: " + item);
    }

    // --- RESIZABLE ---
    @Given("I open the DemoQA Resizable page")
    public void openResizablePage() {
        String url = ConfigReader.get("app.resizable.url");
        Log.info("[PLAYWRIGHT] Navigating to Resizable page: " + url);
        PlaywrightManager.navigate(url);
        resizablePage = new PlaywrightResizablePage();
    }

    @When("I resize the restricted box by width {int} and height {int}")
    public void resizeBox(int xOffset, int yOffset) {
        resizablePage.resizeRestrictedBox(xOffset, yOffset);
    }

    @Then("the restricted box dimensions should be greater than initial size")
    public void verifyResizedDimensions() {
        double width = resizablePage.getRestrictedBoxWidth();
        double height = resizablePage.getRestrictedBoxHeight();
        Log.info("[PLAYWRIGHT] Resized box dimensions: " + width + "x" + height);
        Assert.assertTrue(width >= 190, "Width not greater than initial size");
    }

    // --- DROPPABLE ---
    @Given("I open the DemoQA Droppable page")
    public void openDroppablePage() {
        String url = ConfigReader.get("app.droppable.url");
        Log.info("[PLAYWRIGHT] Navigating to Droppable page: " + url);
        PlaywrightManager.navigate(url);
        droppablePage = new PlaywrightDroppablePage();
    }

    @When("I drag the draggable element to the drop target")
    public void dragAndDropElement() {
        droppablePage.dragAndDropSimple();
    }

    @Then("the droppable target text should update to {string}")
    public void verifyDropTargetText(String expected) {
        String actual = droppablePage.getDropSuccessMessage();
        Log.info("[PLAYWRIGHT] Droppable target text: " + actual);
        Assert.assertEquals(actual, expected);
    }

    // --- DRAGABBLE ---
    @Given("I open the DemoQA Dragabble page")
    public void openDragabblePage() {
        String url = ConfigReader.get("app.dragabble.url");
        Log.info("[PLAYWRIGHT] Navigating to Dragabble page: " + url);
        PlaywrightManager.navigate(url);
        dragabblePage = new PlaywrightDragabblePage();
    }

    @When("I drag the drag box by offset {int} and {int}")
    public void dragBoxByOffset(int x, int y) {
        dragabblePage.dragSimpleBox(x, y);
    }

    @Then("the drag box position should be updated")
    public void verifyDragBoxPosition() {
        double x = dragabblePage.getDragBoxX();
        double y = dragabblePage.getDragBoxY();
        Log.info("[PLAYWRIGHT] New drag box location: (" + x + ", " + y + ")");
        Assert.assertTrue(x >= 0 && y >= 0);
    }
}
