package com.automation.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {

    protected static AppiumDriver driver;
    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeMethod
    public void setUp() {
        try {
            UiAutomator2Options options = new UiAutomator2Options();
            options.setPlatformName("Android");
            options.setDeviceName("emulator-5554");
            options.setAutomationName("UiAutomator2");
            options.setNewCommandTimeout(Duration.ofSeconds(300));
            options.setUdid("emulator-5554");

            driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            logger.info("Driver initialized successfully");
        } catch (MalformedURLException e) {
            logger.error("Failed to initialize driver: " + e.getMessage());
            throw new RuntimeException("Failed to initialize driver", e);
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Driver closed successfully");
        }
    }

    public static AppiumDriver getDriver() {
        return driver;
    }
}