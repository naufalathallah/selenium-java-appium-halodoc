package com.automation.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected AppiumDriver driver;
    protected WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(BasePage.class);

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    protected void click(WebElement element) {
        try {
            wait.until(driver -> element.isDisplayed());
            element.click();
            logger.info("Clicked on element: " + element.toString());
        } catch (Exception e) {
            logger.error("Failed to click on element: " + element.toString(), e);
            throw e;
        }
    }

    protected void sendKeys(WebElement element, String text) {
        try {
            wait.until(driver -> element.isDisplayed());
            element.clear();
            element.sendKeys(text);
            logger.info("Entered text '" + text + "' in element: " + element.toString());
        } catch (Exception e) {
            logger.error("Failed to enter text in element: " + element.toString(), e);
            throw e;
        }
    }

    protected String getText(WebElement element) {
        try {
            wait.until(driver -> element.isDisplayed());
            String text = element.getText();
            logger.info("Retrieved text '" + text + "' from element: " + element.toString());
            return text;
        } catch (Exception e) {
            logger.error("Failed to get text from element: " + element.toString(), e);
            throw e;
        }
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            logger.debug("Element not displayed: " + element.toString());
            return false;
        }
    }

    public void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
            logger.info("Waited for " + seconds + " seconds");
        } catch (InterruptedException e) {
            logger.error("Wait interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}