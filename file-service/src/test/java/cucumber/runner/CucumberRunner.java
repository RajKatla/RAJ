package cucumber.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(glue = { "cucumber.stepdefinition" }, format = {
        "pretty", "html:target/cucumber", "json:target/cucumber.json" }, features = { "classpath:features" })
public class CucumberRunner {
}
