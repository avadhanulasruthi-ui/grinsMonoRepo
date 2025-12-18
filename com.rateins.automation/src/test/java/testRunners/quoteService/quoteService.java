package testRunners.quoteService;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(	features="src/test/java/features/quoteService",
					glue ="stepDefinition",
					monochrome=true, 
					plugin = { "pretty", "json:target/cucumber-reports/Cucumber.json",
							"junit:target/cucumber-reports/Cucumber.xml",
							"html:target/cucumber-reports.html"},
					tags ="@quoteService")
public class quoteService {
}
