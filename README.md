# Halodoc Android Automation Framework

Android mobile automation testing framework using Selenium + Appium with BDD approach using Cucumber.

## 🏗️ Framework Architecture

This framework implements **all required criteria**:

- ✅ **Selenium** - WebDriver for automation
- ✅ **Java** - Programming language
- ✅ **POM (Page Object Model)** - Design pattern for maintainable code
- ✅ **Cucumber (BDD)** - Behavior Driven Development with Gherkin syntax
- ✅ **Maven** - Build and dependency management
- ✅ **TestNG** - Test execution framework
- ✅ **Extent Reports** - Detailed HTML reporting with step-by-step breakdown
- ✅ **RestAssured** - API testing capabilities
- ✅ **Percy** - Visual testing tool

## 📁 Project Structure

```
selenium-java-appium-halodoc/
├── src/
│   ├── main/java/com/automation/
│   │   ├── base/              # BaseTest and BasePage classes
│   │   ├── constants/         # Application constants
│   │   ├── pages/             # Page Object Model classes
│   │   └── utils/             # Utility classes
│   └── test/
│       ├── java/com/automation/
│       │   ├── listeners/     # Test listeners and reporting
│       │   ├── runners/       # Cucumber test runners
│       │   ├── stepdefinitions/ # BDD step definitions
│       │   └── tests/         # TestNG test classes
│       └── resources/
│           ├── features/      # Cucumber feature files (.feature)
│           ├── reports/       # Generated test reports
│           ├── screenshots/   # Test screenshots
│           └── testdata/      # Test data files
├── target/                    # Maven build output
├── pom.xml                   # Maven configuration
└── README.md                 # This documentation
```

## 🛠️ Prerequisites

### Required Software:
1. **Java 17+**
2. **Maven 3.6+**
3. **Android SDK**
4. **Appium Server 2.x**
5. **Android Device/Emulator**

### Device Setup:
- Device ID: `TCJRWCW8NZ6P9HQW`
- Android Version: `13`
- App Package: `com.temandiabetes.android`
- App Activity: `.MainActivity`

## 🚀 Quick Start

### 1. Clone Repository
```bash
git clone <repository-url>
cd selenium-java-appium-halodoc
```

### 2. Install Dependencies
```bash
mvn clean install
```

### 3. Start Appium Server
```bash
# Start Appium server on default port 4723
appium

# Or with specific port
appium --port 4723
```

### 4. Connect Android Device
```bash
# Check connected devices
adb devices

# Ensure device TCJRWCW8NZ6P9HQW is listed
```

## 🧪 Running Tests

### BDD Cucumber Tests (Recommended)

#### Run Specific Test Cases:
```bash
# Run TC-1 only (Successful login)
mvn test -Dtest=TestRunner -Dcucumber.filter.tags="@tc-1"

# Run TC-2 only (Login with different credentials)
mvn test -Dtest=TestRunner -Dcucumber.filter.tags="@tc-2"
```

#### Run by Test Categories:
```bash
# Mobile UI Tests
mvn test -Dtest=TestRunner -Dcucumber.filter.tags="@smoke"
mvn test -Dtest=TestRunner -Dcucumber.filter.tags="@regression"

# API Tests (RestAssured)
mvn test -Dtest=TestRunner -Dcucumber.filter.tags="@api"

# Visual Tests (Percy)
mvn test -Dtest=TestRunner -Dcucumber.filter.tags="@visual"

# Combined Tests
mvn test -Dtest=TestRunner -Dcucumber.filter.tags="@tc-1 and @smoke"
mvn test -Dtest=TestRunner -Dcucumber.filter.tags="@api or @visual"
```

#### Run All BDD Tests:
```bash
# Run all Cucumber tests
mvn test -Dtest=TestRunner

# Clean build and run all tests
mvn clean test -Dtest=TestRunner
```

### Traditional TestNG Tests (Legacy)
```bash
# Run specific TestNG test
mvn test -Dtest=LoginTest

# Run all TestNG tests
mvn test
```

## 📊 Test Reports

### BDD Extent Reports (Detailed)
- **Location**: `src/test/resources/reports/ExtentReport-XX.html`
- **Features**:
  - Step-by-step execution details
  - Scenario names and descriptions
  - Tag categorization (@tc-1, @smoke, @regression)
  - Pass/Fail status for each step
  - Error details and stack traces
  - System information

### Cucumber HTML Reports
- **Location**: `target/cucumber-reports/report.html`
- **Features**:
  - Feature file visualization
  - Scenario execution summary
  - Step definitions mapping

### Additional Reports
- **JSON**: `target/cucumber-reports/report.json`
- **JUnit XML**: `target/cucumber-reports/report.xml`

## 🧪 Test Scenarios

### Login Feature (`src/test/resources/features/Login.feature`)

#### TC-1: Successful Login (@tc-1 @smoke @regression)
```gherkin
Scenario: Successful login with valid email and password
  Given Open Application
  When I enter valid email and password
  And I tap the login button
  Then I should be redirected to the home page
```

#### TC-2: Login Variations (@tc-2 @regression)
```gherkin
Scenario Outline: Login with different credentials
  When I enter email "<email>" and password "<password>"
  And I tap the login button
  Then I should see "<result>"

  Examples:
    | email              | password       | result           |
    | api1@yopmail.com   | Tdautomate123! | homepage         |
    | api1@yopmail.com   | invalidpass    | validation error |
    | empty              | Tdautomate123! | homepage         |
```

## 🔧 Configuration

### Test Data (`src/test/resources/testdata/testdata.json`)
```json
{
  "validEmail": "api1@yopmail.com",
  "validPassword": "Tdautomate123!"
}
```

### Device Configuration (`src/main/java/com/automation/base/BaseTest.java`)
- Platform: Android 13
- Device: TCJRWCW8NZ6P9HQW
- Appium Server: http://localhost:4723
- Automation: UIAutomator2

## 🐛 Troubleshooting

### Common Issues:

#### 1. SessionNotCreated Error
```
Error: Could not start a new session
```
**Solution**:
- Ensure Appium server is running: `appium`
- Check device connection: `adb devices`
- Verify device ID matches `TCJRWCW8NZ6P9HQW`

#### 2. App Not Found
```
Error: Activity not found
```
**Solution**:
- Install Halodoc app on device
- Verify package name: `com.temandiabetes.android`

#### 3. Port Already in Use
```
Error: Port 4723 already in use
```
**Solution**:
```bash
# Kill existing Appium process
lsof -ti:4723 | xargs kill -9

# Start Appium again
appium
```

#### 4. Element Not Found
```
Error: Element not located
```
**Solution**:
- Check accessibility IDs in app
- Verify app version compatibility
- Update locators in Page Object classes

## 📱 Supported Test Types

### 1. **Mobile UI Automation**
- App interaction testing via Appium
- Page Object Model implementation
- BDD scenarios with Cucumber

### 2. **API Testing (RestAssured)**
- HTTP methods: GET, POST, PUT, DELETE
- JSON response validation
- Status code verification
- API endpoint: `https://jsonplaceholder.typicode.com`

### 3. **Visual Testing (Percy)**
- Visual regression testing
- Screenshot comparison
- UI change detection
- Cross-browser visual validation

### 4. **Integrated Reporting**
- Step-by-step execution details
- Screenshot capture on failures
- API response logging
- Visual diff reports

## 🚀 CI/CD Integration

### Maven Commands for CI:
```bash
# Build and test
mvn clean compile test -Dtest=TestRunner

# Generate reports only
mvn test -Dtest=TestRunner -Dcucumber.filter.tags="@smoke"

# Parallel execution (future enhancement)
mvn test -Dtest=TestRunner -DthreadCount=2
```

## 🤝 Contributing

1. Create feature branch from `main`
2. Add/modify feature files in `src/test/resources/features/`
3. Implement step definitions in `src/test/java/com/automation/stepdefinitions/`
4. Update page objects if needed
5. Run tests locally before pushing
6. Create pull request with detailed description

## 📋 Best Practices

1. **BDD First**: Write feature files before implementation
2. **Page Object Pattern**: Keep locators and actions in page classes
3. **Data-Driven**: Use test data files and scenario outlines
4. **Descriptive Naming**: Use clear test case names and tags
5. **Independent Tests**: Each scenario should be independent
6. **Error Handling**: Implement proper exception handling
7. **Reporting**: Always check reports for detailed analysis

---

**Framework Version**: 1.0.0
**Last Updated**: September 2025
**Maintainer**: Automation Team