package br.com.getnet.runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/br/com/getnet/feature", glue = {"br.com.getnet.glue"})
public class GetNetTestRunner {

}
