package com.automation.visual;

import com.automation.base.BaseTest;
import io.percy.selenium.Percy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PercyTestHelper {

    private static final Logger logger = LogManager.getLogger(PercyTestHelper.class);
    private static Percy percy;

    public static void initializePercy() {
        try {
            percy = new Percy(BaseTest.getDriver());
            logger.info("Percy initialized successfully");
        } catch (Exception e) {
            logger.warn("Percy initialization failed: " + e.getMessage());
            logger.info("Visual tests will be skipped if Percy is not configured");
        }
    }

    public static String takeScreenshot(String name) {
        String screenshotPath = null;

        if (percy != null && BaseTest.getDriver() != null) {
            try {
                percy.snapshot(name);
                logger.info("Percy screenshot taken: " + name);
            } catch (Exception e) {
                logger.error("Failed to take Percy screenshot '" + name + "': " + e.getMessage());
            }
        } else {
            logger.warn("Percy not available, taking regular screenshot instead: " + name);
        }

        // Always take regular screenshot for Extent Report
        if (BaseTest.getDriver() != null) {
            try {
                screenshotPath = com.automation.utils.ScreenshotUtils.captureScreenshot(name.replaceAll("[^a-zA-Z0-9]", "_"));
                logger.info("Regular screenshot captured for visual test: " + screenshotPath);
            } catch (Exception e) {
                logger.error("Failed to capture regular screenshot: " + e.getMessage());
            }
        }

        return screenshotPath;
    }

    public static String takeScreenshotWithOptions(String name, int minHeight) {
        String screenshotPath = null;

        if (percy != null && BaseTest.getDriver() != null) {
            try {
                // Percy snapshot with basic configuration
                percy.snapshot(name);
                logger.info("Percy screenshot taken with options: " + name + " (minHeight: " + minHeight + ")");
            } catch (Exception e) {
                logger.error("Failed to take Percy screenshot with options '" + name + "': " + e.getMessage());
            }
        } else {
            logger.warn("Percy not available, taking regular screenshot instead: " + name);
        }

        // Always take regular screenshot for Extent Report
        if (BaseTest.getDriver() != null) {
            try {
                screenshotPath = com.automation.utils.ScreenshotUtils.captureScreenshot(name.replaceAll("[^a-zA-Z0-9]", "_") + "_" + minHeight);
                logger.info("Regular screenshot captured for visual test with options: " + screenshotPath);
            } catch (Exception e) {
                logger.error("Failed to capture regular screenshot: " + e.getMessage());
            }
        }

        return screenshotPath;
    }

    public static boolean isPercyAvailable() {
        return percy != null;
    }

    public static void cleanupPercy() {
        if (percy != null) {
            try {
                logger.info("Percy cleanup completed");
            } catch (Exception e) {
                logger.error("Error during Percy cleanup: " + e.getMessage());
            }
        }
    }
}