package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.listeners.ExtentReportListener;
import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import com.automation.utils.TestDataReader;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ExtentReportListener.class)
public class LoginTest extends BaseTest {

    @Test
    public void testLoginWithValidEmail() {
        LoginPage loginPage = new LoginPage(getDriver());

        String validEmail = TestDataReader.getValidEmail();
        String password = TestDataReader.getValidPassword();

        loginPage.login(validEmail, password);

        HomePage homePage = new HomePage(getDriver());
        Assert.assertTrue(homePage.isTemanDiabetesLogoDisplayed(), "Teman Diabetes logo should be displayed on homepage");
    }
}