package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.listeners.ExtentReportListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.automation.pages.LoginPage;

@Listeners(ExtentReportListener.class)
public class SampleTest extends BaseTest {

    @Test
    public void sampleTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.waitFor(5);
        Assert.assertTrue(true, "Sample test to verify framework setup");
    }

    @Test
    public void verifyDriverInitialization() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.waitFor(10);
        Assert.assertNotNull(getDriver(), "Driver should be initialized");
    }
}