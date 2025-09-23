package com.automation.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApiHooks {

    private static final Logger logger = LogManager.getLogger(ApiHooks.class);

    @Before("@api")
    public void setUpApiTest(Scenario scenario) {
        logger.info("Starting API test scenario: " + scenario.getName());
    }

    @After("@api")
    public void tearDownApiTest(Scenario scenario) {
        if (scenario.isFailed()) {
            logger.error("API test scenario failed: " + scenario.getName());
        } else {
            logger.info("API test scenario passed: " + scenario.getName());
        }
    }
}