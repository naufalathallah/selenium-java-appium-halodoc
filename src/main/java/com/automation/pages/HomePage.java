package com.automation.pages;

import com.automation.base.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    @AndroidFindBy(accessibility = "imgLogoTd")
    private WebElement temanDiabetesLogo;

    public HomePage(AppiumDriver driver) {
        super(driver);
    }

    public boolean isTemanDiabetesLogoDisplayed() {
        waitFor(5);
        return isElementDisplayed(temanDiabetesLogo);
    }
}