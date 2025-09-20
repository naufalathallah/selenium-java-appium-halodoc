claude "Generate these core base classes for Android Appium automation:

1. DriverManager.java - Singleton pattern for managing AndroidDriver instance with:

   - Method to initialize driver with capabilities
   - Method to get driver instance
   - Method to quit driver
   - Support for reading capabilities from JSON file

2. BaseTest.java - TestNG base test class with:

   - @BeforeMethod for driver setup
   - @AfterMethod for driver teardown
   - Screenshot capture on test failure
   - Integration with ExtentReports

3. BasePage.java - Base page object class with:
   - AndroidDriver instance
   - WebDriverWait instance
   - Common methods: click, sendKeys, getText, isDisplayed
   - Wait utility methods
   - Constructor that accepts driver

Use proper Java package structure and include necessary imports."
