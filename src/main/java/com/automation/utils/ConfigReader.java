package com.automation.utils;

import com.automation.constants.AppConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;
    private static final Logger logger = LogManager.getLogger(ConfigReader.class);

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try {
            properties = new Properties();
            FileInputStream fis = new FileInputStream(AppConstants.CONFIG_FILE_PATH);
            properties.load(fis);
            fis.close();
            logger.info("Configuration properties loaded successfully");
        } catch (IOException e) {
            logger.error("Failed to load configuration properties: " + e.getMessage());
            throw new RuntimeException("Failed to load configuration properties", e);
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            logger.warn("Property '" + key + "' not found in configuration file");
        }
        return value;
    }

    public static String getProperty(String key, String defaultValue) {
        String value = properties.getProperty(key, defaultValue);
        if (value.equals(defaultValue)) {
            logger.info("Using default value '" + defaultValue + "' for property '" + key + "'");
        }
        return value;
    }
}