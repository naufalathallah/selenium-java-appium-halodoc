package com.automation.stepdefinitions;

import com.automation.pages.LoginPage;
import com.automation.visual.PercyTestHelper;
import com.automation.base.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.File;

public class VisualStepDefinitions {

    private LoginPage loginPage;
    private String lastScreenshotPath;

    @Given("Percy visual testing is initialized")
    public void percyVisualTestingIsInitialized() {
        PercyTestHelper.initializePercy();
    }

    @When("I am on the login screen")
    public void iAmOnTheLoginScreen() {
        loginPage = new LoginPage(BaseTest.getDriver());
        loginPage.validateSplashScreen();
    }

    @Then("I capture visual screenshot {string}")
    public void iCaptureVisualScreenshot(String screenshotName) {
        lastScreenshotPath = PercyTestHelper.takeScreenshot(screenshotName);

        // Log the screenshot for visibility in reports
        if (lastScreenshotPath != null) {
            System.out.println("Visual screenshot captured: " + lastScreenshotPath);
            String relativePath = "../screenshots/" + new File(lastScreenshotPath).getName();
            System.out.println("Screenshot available at: " + relativePath);
        }
    }

    @Then("I capture visual screenshot {string} with minimum height {int}")
    public void iCaptureVisualScreenshotWithMinimumHeight(String screenshotName, int minHeight) {
        lastScreenshotPath = PercyTestHelper.takeScreenshotWithOptions(screenshotName, minHeight);

        // Log the screenshot for visibility in reports
        if (lastScreenshotPath != null) {
            System.out.println("Visual screenshot with options captured: " + lastScreenshotPath);
            String relativePath = "../screenshots/" + new File(lastScreenshotPath).getName();
            System.out.println("Screenshot available at: " + relativePath);
        }
    }
}