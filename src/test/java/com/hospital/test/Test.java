package com.hospital.test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty",
        "html:target",
        "json:target/json-report.json"},
        features = "src/test/resources/features",
        glue= {"com.hospital.test"},
        monochrome = true
        , tags = "@Test12"
)
public class Test {

}
