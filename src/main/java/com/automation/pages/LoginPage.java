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

    @AndroidFindBy(accessibility = "btnDaftar")
    private WebElement registerButton;

    @AndroidFindBy(accessibility = "btnMasuk")
    private WebElement loginButton;

    @AndroidFindBy(accessibility = "textCreateAccount")
    private WebElement createAccountText;

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
        validateSplashScreen();
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

    public void validateSplashScreen() {
        waitFor(10);

        if (isElementDisplayed(loginButton)) {
            click(loginButton);
        }

        scrollToBottom();

        if (isElementDisplayed(createAccountText)) {
            String sudahPunyaAkunText = getText(createAccountText);
            if ("SUDAH PUNYA AKUN".equals(sudahPunyaAkunText)) {
                click(createAccountText);
            }
        }
    }
}