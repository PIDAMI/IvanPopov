package com.epam.tc.hw5.tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(
    features = {"src/test/resources/features"},
    glue = {"com.epam.tc.hw5.hooks", "com.epam.tc.hw5.steps"}
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {
}
