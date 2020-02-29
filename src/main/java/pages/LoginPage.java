package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.base_page.BasePage;

public class LoginPage extends BasePage {

    @FindBy(id = "username")
    private WebElement inputUsername;

    @FindBy(id = "password")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[@name='submit']")
    private WebElement buttonLogIn;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public LoginPage sendUsername(String username) {
        writeText(username, inputUsername);
        return this;
    }

    public LoginPage sendPassword(String password) {
        writeText(password, inputPassword);
        return this;
    }

    public LoginPage clickOnLogInButton() {
        clickOn(buttonLogIn);
        return this;
    }
}
