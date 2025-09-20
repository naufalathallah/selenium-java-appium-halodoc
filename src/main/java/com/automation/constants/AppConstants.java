package com.automation.constants;

public class AppConstants {

    public static final String PLATFORM_NAME = "Android";
    public static final String AUTOMATION_NAME = "UiAutomator2";
    public static final String APPIUM_SERVER_URL = "http://localhost:4723/wd/hub";
    public static final int IMPLICIT_WAIT_TIMEOUT = 10;
    public static final int EXPLICIT_WAIT_TIMEOUT = 30;
    public static final int NEW_COMMAND_TIMEOUT = 300;

    public static final String CONFIG_FILE_PATH = "src/main/resources/config/config.properties";
    public static final String TEST_DATA_PATH = "src/test/resources/testdata/";
    public static final String SCREENSHOTS_PATH = "src/test/resources/screenshots/";
    public static final String REPORTS_PATH = "src/test/resources/reports/";

    public static final String EXTENT_REPORT_PATH = REPORTS_PATH + "ExtentReport.html";
    public static final String LOG_FILE_PATH = "logs/automation.log";

    private AppConstants() {
    }
}