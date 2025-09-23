package com.automation.stepdefinitions;

import com.automation.base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends BaseTest {

    @Before
    public void setUp(Scenario scenario) {
        super.setUp();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println("Scenario failed: " + scenario.getName());

            // Capture screenshot BEFORE closing driver
            try {
                if (getDriver() != null) {
                    String scenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");
                    String screenshotPath = com.automation.utils.ScreenshotUtils.captureScreenshot(scenarioName + "_FAILED");
                    System.out.println("Screenshot captured: " + screenshotPath);
                }
            } catch (Exception e) {
                System.out.println("Failed to capture screenshot: " + e.getMessage());
            }
        }
        super.tearDown();
    }
}