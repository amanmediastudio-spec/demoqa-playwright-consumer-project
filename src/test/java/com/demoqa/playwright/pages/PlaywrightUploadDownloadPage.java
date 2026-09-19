package com.demoqa.playwright.pages;

import com.automation.playwright.PlaywrightBasePage;
import com.automation.playwright.PlaywrightPageElement;
import com.automation.utils.Log;
import com.microsoft.playwright.Download;

import java.io.File;
import java.nio.file.Paths;

public class PlaywrightUploadDownloadPage extends PlaywrightBasePage {

    public PlaywrightPageElement downloadButton;
    public PlaywrightPageElement uploadFileInput;
    public PlaywrightPageElement uploadedFilePath;

    public PlaywrightUploadDownloadPage() {
        super("PlaywrightUploadDownloadPage");
    }

    @Override
    protected void initElements() {
        downloadButton = register("downloadButton", "Download file button", "#downloadButton");
        uploadFileInput = register("uploadFileInput", "File upload input element", "#uploadFile");
        uploadedFilePath = register("uploadedFilePath", "Uploaded file result path label", "#uploadedFilePath");
    }

    public void validateAndHealPageElements() {
        Log.info("[PLAYWRIGHT] Validating select menu elements");
        super.validateAndHealPageElements();
    }
    public void clickDownloadButton() {
        Log.info("[PLAYWRIGHT] Triggering file download");
        Download download = getPage().waitForDownload(() -> {
            click(downloadButton);
        });
        Log.info("[PLAYWRIGHT] Download completed: " + download.suggestedFilename());
    }

    public void uploadFile(String relativeOrAbsolutePath) {
        Log.info("[PLAYWRIGHT] Uploading file: " + relativeOrAbsolutePath);
        File file = new File(relativeOrAbsolutePath);
        setInputFiles(uploadFileInput, Paths.get(file.getAbsolutePath()));
    }

    public String getUploadedFilePathText() {
        return getText(uploadedFilePath).trim();
    }
}
