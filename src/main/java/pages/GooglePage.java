package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.base_page.BasePage;

public class GooglePage extends BasePage {

    @FindBy(name = "q")
    private WebElement searchBox;

    @FindBy(xpath = "//input[@name='btnK']")
    private WebElement searchButton;

    public GooglePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public GooglePage sendTextIntoSearchBox(String text) {
        writeText(text, searchBox);
        return this;
    }

    public GooglePage clickOnSearchButton() {
        clickOn(searchButton);
        return this;
    }
}
