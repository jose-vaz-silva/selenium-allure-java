package scenariosRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;



@RunWith(Cucumber.class)
@CucumberOptions(plugin= {"pretty", "html:target/cucumber-html-report", "io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm"}, 
				features = {"src/test/resources/features/CT01.feature"},
				glue = {"steps", "core"},
				monochrome = true)
public class RunnerCT01{
}
