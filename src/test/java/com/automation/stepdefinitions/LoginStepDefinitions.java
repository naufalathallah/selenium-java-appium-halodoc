package com.automation.stepdefinitions;

import com.automation.base.BaseTest;
import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import com.automation.utils.TestDataReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginStepDefinitions {

    private LoginPage loginPage;
    private HomePage homePage;

    @Given("Open Application")
    public void openApplication() {
        loginPage = new LoginPage(BaseTest.getDriver());
        loginPage.validateSplashScreen();
    }

    @When("I enter valid email and password")
    public void iEnterValidEmailAndPassword() {
        String validEmail = TestDataReader.getValidEmail();
        String password = TestDataReader.getValidPassword();
        loginPage.enterEmail(validEmail);
        loginPage.enterPassword(password);
    }

    @When("I enter email {string} and password {string}")
    public void iEnterEmailAndPassword(String email, String password) {
        if ("valid@email.com".equals(email)) {
            email = TestDataReader.getValidEmail();
        }
        if ("validpass".equals(password)) {
            password = TestDataReader.getValidPassword();
        }
        if (!"empty".equals(email)) {
            loginPage.enterEmail(email);
        }
        if (!"empty".equals(password)) {
            loginPage.enterPassword(password);
        }
    }

    @When("I tap the login button")
    public void iTapTheLoginButton() {
        loginPage.clickSubmitButton();
    }

    @Then("I should be redirected to the home page")
    public void iShouldBeRedirectedToTheHomePage() {
        homePage = new HomePage(BaseTest.getDriver());
        Assert.assertTrue(homePage.isTemanDiabetesLogoDisplayed(),
            "User should be redirected to home page");
    }

    @Then("I should see {string}")
    public void iShouldSee(String expectedResult) {
        homePage = new HomePage(BaseTest.getDriver());
        switch (expectedResult) {
            case "homepage":
                Assert.assertTrue(homePage.isTemanDiabetesLogoDisplayed(),
                    "Should see homepage");
                break;
            case "validation error":
                Assert.assertFalse(homePage.isTemanDiabetesLogoDisplayed(),
                    "Should not see homepage");
                break;
            default:
                Assert.fail("Unknown expected result: " + expectedResult);
        }
    }
}