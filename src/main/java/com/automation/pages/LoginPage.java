package com.automation.pages;

import com.automation.base.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    @AndroidFindBy(accessibility = "inputEmail")
    private WebElement emailInput;

    @AndroidFindBy(accessibility = "inputPassword")
    private WebElement passwordInput;

    @AndroidFindBy(accessibility = "btnSubmit")
    private WebElement submitButton;

    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    public void enterEmail(String email) {
        sendKeys(emailInput, email);
    }

    public void enterPassword(String password) {
        sendKeys(passwordInput, password);
    }

    public void clickSubmitButton() {
        click(submitButton);
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickSubmitButton();
    }

    public boolean isEmailInputDisplayed() {
        return isElementDisplayed(emailInput);
    }

    public boolean isPasswordInputDisplayed() {
        return isElementDisplayed(passwordInput);
    }

    public boolean isSubmitButtonDisplayed() {
        return isElementDisplayed(submitButton);
    }

    public boolean isLoginPageDisplayed() {
        return isEmailInputDisplayed() && isPasswordInputDisplayed() && isSubmitButtonDisplayed();
    }
}