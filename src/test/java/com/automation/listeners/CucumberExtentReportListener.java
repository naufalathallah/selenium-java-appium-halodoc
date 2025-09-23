package com.automation.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.*;

import java.io.File;

public class CucumberExtentReportListener implements EventListener {

    private static ExtentReports extentReports;
    private static ExtentTest scenarioTest;
    private static ExtentTest stepTest;

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestRunStarted.class, this::onTestRunStarted);
        publisher.registerHandlerFor(TestCaseStarted.class, this::onTestCaseStarted);
        publisher.registerHandlerFor(TestStepStarted.class, this::onTestStepStarted);
        publisher.registerHandlerFor(TestStepFinished.class, this::onTestStepFinished);
        publisher.registerHandlerFor(TestCaseFinished.class, this::onTestCaseFinished);
        publisher.registerHandlerFor(TestRunFinished.class, this::onTestRunFinished);
    }

    private void onTestRunStarted(TestRunStarted event) {
        File reportDir = new File("src/test/resources/reports");
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }

        int reportCounter = getNextAvailableReportCounter();
        String reportPath = "src/test/resources/reports/ExtentReport-" + reportCounter + ".html";

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle("Cucumber BDD Test Report");
        sparkReporter.config().setReportName("Halodoc Automation Test Results");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Application", "Halodoc Android App");
        extentReports.setSystemInfo("Platform", "Android");
        extentReports.setSystemInfo("Framework", "Selenium + Appium + Cucumber");
        extentReports.setSystemInfo("Test Type", "BDD Automation");
    }

    private void onTestCaseStarted(TestCaseStarted event) {
        String scenarioName = event.getTestCase().getName();
        String featureName = event.getTestCase().getUri().toString().replaceAll(".*/(.*)\\.feature", "$1");

        scenarioTest = extentReports.createTest(scenarioName)
                .assignCategory(featureName)
                .assignAuthor("Automation Team");

        // Add tags to report
        event.getTestCase().getTags().forEach(tag ->
            scenarioTest.assignCategory(tag)
        );
    }

    private void onTestStepStarted(TestStepStarted event) {
        if (event.getTestStep() instanceof PickleStepTestStep) {
            PickleStepTestStep pickleStep = (PickleStepTestStep) event.getTestStep();
            String stepText = pickleStep.getStep().getText();
            String keyword = pickleStep.getStep().getKeyword();

            stepTest = scenarioTest.createNode(keyword + stepText);
        }
    }

    private void onTestStepFinished(TestStepFinished event) {
        if (stepTest != null && event.getTestStep() instanceof PickleStepTestStep) {
            switch (event.getResult().getStatus()) {
                case PASSED:
                    stepTest.log(Status.PASS, "Step executed successfully");
                    break;
                case FAILED:
                    stepTest.log(Status.FAIL, "Step failed: " + event.getResult().getError().getMessage());
                    if (event.getResult().getError() != null) {
                        stepTest.log(Status.FAIL, event.getResult().getError());
                    }
                    break;
                case SKIPPED:
                    stepTest.log(Status.SKIP, "Step was skipped");
                    break;
                case PENDING:
                    stepTest.log(Status.WARNING, "Step is pending implementation");
                    break;
                default:
                    stepTest.log(Status.INFO, "Step status: " + event.getResult().getStatus());
            }
        }
    }

    private void onTestCaseFinished(TestCaseFinished event) {
        if (scenarioTest != null) {
            switch (event.getResult().getStatus()) {
                case PASSED:
                    scenarioTest.log(Status.PASS, "Scenario completed successfully");
                    break;
                case FAILED:
                    scenarioTest.log(Status.FAIL, "Scenario failed: " + event.getResult().getError().getMessage());
                    break;
                case SKIPPED:
                    scenarioTest.log(Status.SKIP, "Scenario was skipped");
                    break;
                default:
                    scenarioTest.log(Status.INFO, "Scenario status: " + event.getResult().getStatus());
            }
        }
    }

    private void onTestRunFinished(TestRunFinished event) {
        if (extentReports != null) {
            extentReports.flush();
            System.out.println("Cucumber Extent Report generated successfully!");
        }
    }

    private static int getNextAvailableReportCounter() {
        File reportsDir = new File("src/test/resources/reports");
        if (!reportsDir.exists()) {
            return 1;
        }

        int maxCounter = 0;
        File[] files = reportsDir.listFiles();
        if (files != null) {
            for (File file : files) {
                String fileName = file.getName();
                if (fileName.startsWith("ExtentReport-") && fileName.endsWith(".html")) {
                    try {
                        String numberStr = fileName.substring(13, fileName.length() - 5);
                        int counter = Integer.parseInt(numberStr);
                        maxCounter = Math.max(maxCounter, counter);
                    } catch (NumberFormatException e) {
                        // Ignore files that don't match the pattern
                    }
                }
            }
        }
        return maxCounter + 1;
    }
}