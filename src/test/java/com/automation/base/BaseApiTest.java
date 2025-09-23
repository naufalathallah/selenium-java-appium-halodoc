package com.automation.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class BaseApiTest {

    private static final Logger logger = LogManager.getLogger(BaseApiTest.class);

    @BeforeClass
    public void setUpApiTests() {
        logger.info("Setting up API test environment");
        // No driver initialization needed for API tests
    }

    @AfterClass
    public void tearDownApiTests() {
        logger.info("API tests completed");
        // No driver cleanup needed
    }
}