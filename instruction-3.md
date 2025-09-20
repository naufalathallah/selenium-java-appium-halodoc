claude "Generate configuration files for Android automation:

1. pom.xml - Maven configuration with:

   - Java 11 configuration
   - Dependencies: Appium Java Client 8.6.0, Selenium 4.15.0, TestNG 7.8.0, ExtentReports 5.0.9, Jackson databind, Log4j2
   - Maven Surefire plugin for TestNG execution
   - Maven compiler plugin with Java 11

2. config.properties - Application configuration with:

   - Appium server URL
   - Platform name, device name
   - App package and activity
   - Timeout values
   - Report paths

3. capabilities.json - Appium capabilities with:

   - Android platform configuration
   - UiAutomator2 automation
   - Device and app settings
   - Additional capabilities like autoGrantPermissions

4. testng.xml - TestNG suite configuration for parallel execution

5. .gitignore - For Java/Maven project excluding target/, logs/, reports/"
