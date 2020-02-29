package step_definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.ModelSelectPage;
import step_definitions.step_hooks.StepHooks;

import static org.assertj.core.api.Assertions.assertThat;

public class ModelSelectDefinitions {

    private WebDriver driver;
    private ModelSelectPage modelSelectPage;

    public ModelSelectDefinitions(StepHooks stepHooks) {
        this.driver = stepHooks.getDriver();
        modelSelectPage = new ModelSelectPage(driver);
    }

    @Then("I can see {string} model displayed")
    public void iCanSeeSupplyChainModelDisplayed(String title) {
        assertThat(modelSelectPage.getModelTitles())
                .as("Check all displayed model title, and compare if they contain expected one")
                .contains(title);
    }

    @And("I clicked on the model {string}")
    public void iClickedOnTheModel(String title) {
        modelSelectPage.clickOnExpectedTitle(title);
    }

}
