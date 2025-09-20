package com.automation.utils;

import com.automation.base.BaseTest;
import com.automation.constants.AppConstants;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

public class ScreenshotUtils {

    private static final Logger logger = LogManager.getLogger(ScreenshotUtils.class);
    private static int screenshotCounter = -1; // Initialize to -1 so first call sets it correctly

    public static String captureScreenshot(String testName) {
        try {
            // Check if driver exists
            if (BaseTest.getDriver() == null) {
                logger.warn("Driver is null, cannot capture screenshot");
                return null;
            }

            TakesScreenshot takesScreenshot = (TakesScreenshot) BaseTest.getDriver();
            File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

            // Initialize counter based on existing files if not already done
            if (screenshotCounter == -1) {
                screenshotCounter = getNextAvailableCounter();
            } else {
                screenshotCounter++;
            }

            String screenshotName = "screenshot-" + screenshotCounter + ".png";
            String screenshotPath = AppConstants.SCREENSHOTS_PATH + screenshotName;

            File destFile = new File(screenshotPath);
            destFile.getParentFile().mkdirs();
            FileUtils.copyFile(sourceFile, destFile);

            logger.info("Screenshot captured: " + screenshotPath);
            return screenshotPath;
        } catch (Exception e) {
            logger.error("Failed to capture screenshot for test '" + testName + "': " + e.getMessage(), e);
            return null;
        }
    }

    public static String captureScreenshot() {
        return captureScreenshot("screenshot");
    }

    public static void resetCounter() {
        screenshotCounter = -1;
        logger.info("Screenshot counter reset");
    }

    public static int getCurrentCounter() {
        return screenshotCounter;
    }

    private static int getNextAvailableCounter() {
        File screenshotsDir = new File(AppConstants.SCREENSHOTS_PATH);
        if (!screenshotsDir.exists()) {
            return 1; // Start from 1 if directory doesn't exist
        }

        int maxCounter = 0;
        File[] files = screenshotsDir.listFiles();
        if (files != null) {
            for (File file : files) {
                String fileName = file.getName();
                if (fileName.startsWith("screenshot-") && fileName.endsWith(".png")) {
                    try {
                        String numberStr = fileName.substring(11, fileName.length() - 4); // Extract number from "screenshot-X.png"
                        int counter = Integer.parseInt(numberStr);
                        maxCounter = Math.max(maxCounter, counter);
                    } catch (NumberFormatException e) {
                        // Ignore files that don't match the pattern
                    }
                }
            }
        }
        return maxCounter + 1; // Return next available number
    }
}