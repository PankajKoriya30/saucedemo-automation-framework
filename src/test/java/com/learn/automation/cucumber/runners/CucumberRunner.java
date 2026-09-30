package com.learn.automation.cucumber.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.learn.automation.cucumber.stepdefinitions",
        tags = "@smoke",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html"
        },
        monochrome = true
)
public class CucumberRunner extends AbstractTestNGCucumberTests {
}
