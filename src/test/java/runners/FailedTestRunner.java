package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"api.zippopotam.stepDef", "ui.sogeti.stepDef"},
        features = "@target/rerun.txt"
)

public class FailedTestRunner {

}
