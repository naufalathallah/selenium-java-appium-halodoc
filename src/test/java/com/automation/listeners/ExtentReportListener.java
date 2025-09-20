package com.automation.listeners;

import com.automation.constants.AppConstants;
import com.automation.utils.ScreenshotUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class ExtentReportListener implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static final Logger logger = LogManager.getLogger(ExtentReportListener.class);
    private static int reportCounter = -1; // Initialize to -1 so first call sets it correctly

    @Override
    public void onStart(org.testng.ITestContext context) {
        // Create reports directory if it doesn't exist
        File reportsDir = new File(AppConstants.REPORTS_PATH);
        if (!reportsDir.exists()) {
            reportsDir.mkdirs();
        }

        // Generate incremental report name
        if (reportCounter == -1) {
            reportCounter = getNextAvailableReportCounter();
        } else {
            reportCounter++;
        }
        String reportPath = AppConstants.REPORTS_PATH + "ExtentReport-" + reportCounter + ".html";

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle("Android Automation Test Report");
        sparkReporter.config().setReportName("Test Execution Report - " + reportCounter);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Platform", "Android");
        extent.setSystemInfo("Environment", "Test");
        extent.setSystemInfo("User", System.getProperty("user.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("OS", System.getProperty("os.name"));

        logger.info("Extent Report initialized at: " + reportPath);
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
        logger.info("Test started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test passed successfully");
        logger.info("Test passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            extentTest.get().log(Status.FAIL, "Test failed: " + result.getThrowable().getMessage());

            // Capture screenshot on failure
            String screenshotPath = ScreenshotUtils.captureScreenshot(result.getMethod().getMethodName() + "_FAILED");
            if (screenshotPath != null) {
                // Convert to relative path from reports directory to screenshots directory
                String relativePath = "../screenshots/" + new File(screenshotPath).getName();
                extentTest.get().addScreenCaptureFromPath(relativePath);
                logger.info("Screenshot captured for failed test: " + screenshotPath);
                logger.info("Relative path added to report: " + relativePath);
            } else {
                logger.warn("Failed to capture screenshot for test: " + result.getMethod().getMethodName());
            }

            // Log full stack trace
            extentTest.get().log(Status.FAIL, "Full Exception: " + result.getThrowable().toString());

            logger.error("Test failed: " + result.getMethod().getMethodName(), result.getThrowable());
        } catch (Exception e) {
            logger.error("Error in onTestFailure handler: " + e.getMessage(), e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, "Test skipped: " + result.getThrowable());
        logger.info("Test skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        try {
            if (extent != null) {
                extent.flush();
                logger.info("Extent Report generated successfully");
                logger.info("Report location: " + AppConstants.REPORTS_PATH);
            } else {
                logger.warn("ExtentReports instance is null, report may not have been generated");
            }
        } catch (Exception e) {
            logger.error("Error during report finalization: " + e.getMessage(), e);
        }
    }

    public static ExtentTest getExtentTest() {
        return extentTest.get();
    }

    private static int getNextAvailableReportCounter() {
        File reportsDir = new File(AppConstants.REPORTS_PATH);
        if (!reportsDir.exists()) {
            return 1; // Start from 1 if directory doesn't exist
        }

        int maxCounter = 0;
        File[] files = reportsDir.listFiles();
        if (files != null) {
            for (File file : files) {
                String fileName = file.getName();
                if (fileName.startsWith("ExtentReport-") && fileName.endsWith(".html")) {
                    try {
                        String numberStr = fileName.substring(13, fileName.length() - 5); // Extract number from "ExtentReport-X.html"
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