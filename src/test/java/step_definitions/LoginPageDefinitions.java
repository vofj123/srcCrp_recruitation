package step_definitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import step_definitions.step_hooks.StepHooks;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginPageDefinitions {
    private WebDriver driver;
    private LoginPage loginPage;

    public LoginPageDefinitions(StepHooks stepHooks) {
        this.driver = stepHooks.getDriver();
        loginPage = new LoginPage(driver);
    }

    @Given("I am on the website with URL: {word}")
    public void iAmOnTheWebsiteWithURL(String url) {
        loginPage.getWebsiteWithURL(url);
    }

    @When("I log in with following credentials:")
    public void iLogInWithFollowingCredentials(DataTable dt) {
        Map<String, String> data = dt.asMap(String.class, String.class);
        loginPage.sendUsername(data.get("username"))
                .sendPassword(data.get("password"))
                .clickOnLogInButton();
    }

}
