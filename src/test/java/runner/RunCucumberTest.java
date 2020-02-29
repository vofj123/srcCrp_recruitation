package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/cucumber-report.json", "pretty", "html:target/cucumber-tests"},
        features = {"src/test/java/features"},
        glue = {"step_definitions"}
)
public class RunCucumberTest {
}
