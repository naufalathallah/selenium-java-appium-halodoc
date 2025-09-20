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

public class ExtentReportListener implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static final Logger logger = LogManager.getLogger(ExtentReportListener.class);

    @Override
    public void onStart(org.testng.ITestContext context) {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(AppConstants.EXTENT_REPORT_PATH);
        sparkReporter.config().setDocumentTitle("Android Automation Test Report");
        sparkReporter.config().setReportName("Test Execution Report");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Platform", "Android");
        extent.setSystemInfo("Environment", "Test");
        extent.setSystemInfo("User", System.getProperty("user.name"));

        logger.info("Extent Report initialized");
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
        extentTest.get().log(Status.FAIL, "Test failed: " + result.getThrowable());

        String screenshotPath = ScreenshotUtils.captureScreenshot(result.getMethod().getMethodName());
        if (screenshotPath != null) {
            extentTest.get().addScreenCaptureFromPath(screenshotPath);
        }

        logger.error("Test failed: " + result.getMethod().getMethodName(), result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, "Test skipped: " + result.getThrowable());
        logger.info("Test skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        extent.flush();
        logger.info("Extent Report generated successfully");
    }

    public static ExtentTest getExtentTest() {
        return extentTest.get();
    }
}