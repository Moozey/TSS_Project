package com.example.tss_project_withspring;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/", tags = "E2E", plugin = {"json:target/cucumber-report.json", "html:target/testing/cucumber-report.html"},
        glue = "com.example.tss_project_withspring.steps")
public class AuthorE2ETests {

}

