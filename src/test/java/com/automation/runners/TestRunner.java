package com.automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.automation.stepdefinitions"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/report.html",
                "json:target/cucumber-reports/report.json",
                "junit:target/cucumber-reports/report.xml",
                "com.automation.listeners.CucumberExtentReportListener"
        },
        monochrome = true,
        tags = "@smoke or @regression"
)
@Listeners({}) // Remove ExtentReportListener to avoid conflict
public class TestRunner extends AbstractTestNGCucumberTests {
}