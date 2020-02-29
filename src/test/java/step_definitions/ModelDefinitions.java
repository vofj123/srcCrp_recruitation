package step_definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.ModelPage;
import step_definitions.step_hooks.StepHooks;

import static org.assertj.core.api.Assertions.assertThat;

public class ModelDefinitions {
    private WebDriver driver;
    private ModelPage modelPage;

    public ModelDefinitions(StepHooks stepHooks) {
        this.driver = stepHooks.getDriver();
        modelPage = new ModelPage(driver);
    }

    @When("I open Contents section if closed")
    public void iOpenContentsSectionIfClosed() {
        modelPage.expandContentsIfNotExpanded();
    }

    @And("I open Detailed Demand Review")
    public void iOpenDetailedDemandReview() {
        modelPage.clickOnDetailedDemandReview();
    }

    @Then("I verify the title is {string}")
    public void iVerifyTheTitleIs(String title) {
        assertThat(modelPage.getTitleOfBoard())
                .as("Comparing title values")
                .isEqualTo(title);
    }

    @And("I am at {string} view")
    public void iAmAtView(String title) {
        modelPage.expandContentsIfNotExpanded();
        modelPage.clickOnDetailedDemandReview();
        assertThat(modelPage.getTitleOfBoard())
                .as("Comparing title values")
                .isEqualTo(title);
    }

    @When("I change filter to {string}")
    public void iChangeFilterTo(String filterValue) {
        Assert.fail("not implemented");

//        1. Click on arrow expanding the filters
//        2. Put filterValue into searchbox
//        3. Choose found filter
    }

    @And("I select {string} checkbox in {string}")
    public void iSelectOverrideCheckboxIn(String rowValue) {
//        1. Find row title by rowValue and store its id (it contains x value which we need and y=0)
//        2. Using the id of row title I would manipulate it (x value of previous and y=1) to have corresponding Override? id value
//        3. Might be the problem that field is not visible (need to move the bar) -> not sure how to do this yet, need to test it out
//        4. click on it
    }

    @And("I put value into corresponding Override Forecast")
    public void iPutValueIntoCorrespondingOverrideForecast() {
//        1. Read value using strategy -> get id of row title and look for its children
//        2. Put value + 10 into x , y=2 field
//        end of time..
    }
}
