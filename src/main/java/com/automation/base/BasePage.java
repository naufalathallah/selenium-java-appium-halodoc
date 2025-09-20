package com.automation.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;

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

    public void scrollToBottom() {
        try {
            Dimension size = driver.manage().window().getSize();
            int startX = size.width / 2;
            int startY = (int) (size.height * 0.8);
            int endX = size.width / 2;
            int endY = (int) (size.height * 0.2);

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence scroll = new Sequence(finger, 1);

            scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
            scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            scroll.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, endY));
            scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(Arrays.asList(scroll));

            logger.info("Scrolled to bottom of the page");
        } catch (Exception e) {
            logger.error("Failed to scroll to bottom: " + e.getMessage());
            throw e;
        }
    }
}