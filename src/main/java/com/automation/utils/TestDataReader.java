package com.automation.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class TestDataReader {

    private static JsonNode testData;
    private static final Logger logger = LogManager.getLogger(TestDataReader.class);
    private static final String TEST_DATA_PATH = "src/test/resources/testdata/testdata.json";

    static {
        loadTestData();
    }

    private static void loadTestData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            testData = mapper.readTree(new File(TEST_DATA_PATH));
            logger.info("Test data loaded successfully from: " + TEST_DATA_PATH);
        } catch (IOException e) {
            logger.error("Failed to load test data: " + e.getMessage());
            throw new RuntimeException("Failed to load test data", e);
        }
    }

    public static String getValidEmail() {
        return testData.get("testData").get("validEmail").asText();
    }

    public static String getValidPassword() {
        return testData.get("testData").get("validPassword").asText();
    }
}