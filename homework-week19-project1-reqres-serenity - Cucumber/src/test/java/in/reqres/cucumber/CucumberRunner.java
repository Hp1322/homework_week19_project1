package in.reqres.cucumber;



import cucumber.api.CucumberOptions;
import in.reqres.testbase.TestBase;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Created by Harsh
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/java/resources/feature/"
//, tags = "@Test"
)
public class CucumberRunner extends TestBase {



}
