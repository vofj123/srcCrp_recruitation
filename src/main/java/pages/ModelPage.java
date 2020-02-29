package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.base_page.BasePage;

public class ModelPage extends BasePage {

    @FindBy(xpath = "//span[contains(@class,'dojoxExpandoTitleNode')]")
    private WebElement buttonContents;

    @FindBy(xpath = "//div[contains(@class,'dojoxExpandoIconLeft')]")
    private WebElement buttonExpand;

    @FindBy(xpath = "//a[contains(text(),'Detailed Demand Review')]")
    private WebElement buttonDetailedDemandReview;

    @FindBy(xpath = "//span[contains(@class,'icon-dashboardMenuCollapsed')]")
    private WebElement buttonExpandDemand;

    @FindBy(xpath = "//div[contains(@class,'valueContainer')]")
    private WebElement boardTitle;

    public ModelPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ModelPage clickOnExpand() {
        clickOn(buttonExpand);
        return this;
    }

    public ModelPage clickOnExpandingDemands() {
        clickOn(buttonExpandDemand);
        return this;
    }

    public ModelPage expandContentsIfNotExpanded() {
        if (!isDisplayedWithTimeout(buttonContents, 10)) {
            clickOnExpand();
        }
        clickOn(buttonContents);
        return this;
    }

    public ModelPage clickOnDetailedDemandReview() {
        if (!isDisplayedWithTimeout(buttonDetailedDemandReview, 10)) {
            clickOnExpandingDemands();
        }
        clickOn(buttonDetailedDemandReview);
        return this;
    }

    public String getTitleOfBoard() {
        String title = "";
        if (isDisplayedWithTimeout(boardTitle, 10)) {
            title = boardTitle.getText();
        } else {
            Assert.fail("Did not found title");
        }
        return title;
    }
}
