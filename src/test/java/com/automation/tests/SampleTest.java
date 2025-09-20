package com.automation.tests;

import com.automation.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleTest extends BaseTest {

    @Test
    public void sampleTest() {
        Assert.assertTrue(true, "Sample test to verify framework setup");
    }

    @Test
    public void verifyDriverInitialization() {
        Assert.assertNotNull(getDriver(), "Driver should be initialized");
    }
}