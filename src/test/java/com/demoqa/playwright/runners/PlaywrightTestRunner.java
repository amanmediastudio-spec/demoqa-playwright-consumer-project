package com.demoqa.playwright.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {
                "com.demoqa.playwright.stepdefinitions",
                "com.automation.hooks.playwright"
        },
        plugin = {
                "pretty",
                "html:target/cucumber-reports/playwright-report.html",
                "json:target/cucumber-reports/playwright-report.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
public class PlaywrightTestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @org.testng.annotations.AfterSuite
    public void afterSuite() {
        com.automation.ai.HealingAuditLogger.exportJsonReport();
    }
}
