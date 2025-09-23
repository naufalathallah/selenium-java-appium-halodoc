package com.automation.stepdefinitions;

import com.automation.base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends BaseTest {

    @Before
    public void setUp(Scenario scenario) {
        super.setUp();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println("Scenario failed: " + scenario.getName());
        }
        super.tearDown();
    }
}