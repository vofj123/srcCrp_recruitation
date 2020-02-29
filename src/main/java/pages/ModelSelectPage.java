package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.base_page.BasePage;

import java.util.ArrayList;
import java.util.List;

public class ModelSelectPage extends BasePage {

    @FindBy(xpath = "//div[contains(@class,'front')]//div[contains(@class,'title')]")
    private List<WebElement> modelTitleList;

    public ModelSelectPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public List<String> getModelTitles() {
        List<String> listOfFoundTitles = new ArrayList<>();
        if (getModelTitleListSize() > 0) {
            for (WebElement element : modelTitleList) {
                listOfFoundTitles.add(element.getText());
            }
        } else {
            Assert.fail("Did not found any models");
        }
        return listOfFoundTitles;
    }

    public ModelSelectPage clickOnExpectedTitle(String expectedTitle) {
        if (getModelTitleListSize() > 0) {
            for (WebElement element : modelTitleList) {
                if (element.getText().equalsIgnoreCase(expectedTitle)){
                    clickOn(element);
                    break;
                }
            }
        } else {
            Assert.fail("Did not found any models");
        }
        return this;
    }

    private int getModelTitleListSize() {
        return modelTitleList.size();
    }
}
