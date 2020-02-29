package step_definitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.GooglePage;
import step_definitions.step_hooks.StepHooks;

import static org.assertj.core.api.Assertions.assertThat;

public class GoogleDefinitions {
    private WebDriver driver;
    private GooglePage googlePage;

    public GoogleDefinitions(StepHooks stepHooks) {
        this.driver = stepHooks.getDriver();
        googlePage = new GooglePage(driver);
    }

    @Given("I am on the website with URL: {word}")
    public void iAmOnTheWebsiteWithURL(String url) {
        googlePage.getWebsiteWithURL(url);
    }

    @When("I search for: {word}")
    public void iSearchForKeyWord(String keyWord) {
        googlePage.sendTextIntoSearchBox(keyWord);
        googlePage.clickOnSearchButton();
    }

    @Then("I see results for: {word}")
    public void iSeeResultsForKeyWord(String keyWord) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(driver.getPageSource())
                .contains(keyWord);
    }
}
