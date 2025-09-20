package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testLoginWithValidEmail() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.waitFor(10);
        
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be displayed");
        String validEmail = "api1@yopmail.com";
        String password = "Tdautomate123!";

        loginPage.enterEmail(validEmail);
        loginPage.enterPassword(password);
        loginPage.clickSubmitButton();

        // Add assertions based on expected behavior
    }
}