package com.automation.utils;

import com.automation.base.BaseTest;
import com.automation.constants.AppConstants;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtils {

    private static final Logger logger = LogManager.getLogger(ScreenshotUtils.class);
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

    public static String captureScreenshot(String testName) {
        try {
            TakesScreenshot takesScreenshot = (TakesScreenshot) BaseTest.getDriver();
            File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

            String timestamp = LocalDateTime.now().format(DATE_FORMAT);
            String screenshotName = testName + "_" + timestamp + ".png";
            String screenshotPath = AppConstants.SCREENSHOTS_PATH + screenshotName;

            File destFile = new File(screenshotPath);
            destFile.getParentFile().mkdirs();
            FileUtils.copyFile(sourceFile, destFile);

            logger.info("Screenshot captured: " + screenshotPath);
            return screenshotPath;
        } catch (IOException e) {
            logger.error("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }

    public static String captureScreenshot() {
        return captureScreenshot("screenshot");
    }
}