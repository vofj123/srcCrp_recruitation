package pages.base_page;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor jsExecutor;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
        jsExecutor = ((JavascriptExecutor) driver);
    }

    public void getWebsiteWithURL(String url) {
        driver.get(url);
    }

    protected WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement clickOn(WebElement element) {
        waitForClickable(element).click();
        return element;
    }

    protected WebElement writeText(String text, WebElement inputElement) {
        waitForVisibility(inputElement);
        inputElement.sendKeys(text);
        return inputElement;
    }

    protected WebElement clearAndWriteText(String text, WebElement inputElement) {
        waitForVisibility(inputElement);
        inputElement.clear();
        inputElement.sendKeys(text);
        return inputElement;
    }

    protected void scrollIntoView(WebElement element) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", element);
    }

    protected void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ignored) {
        }
    }

    protected Actions getActions() {
        return new Actions(driver);
    }

    protected void moveToElement(WebElement webElement) {
        waitForVisibility(webElement);
        scrollIntoView(webElement);
        getActions().moveToElement(webElement).build().perform();
    }

    protected boolean isDisplayedWithTimeout(WebElement element, long timeOut) {
        long startTime = System.currentTimeMillis();
        while (!isTimeout(startTime, timeOut)) {
            try {
                if (element.isDisplayed()) {
                    return true;
                }
                sleep(500);
            } catch (NoSuchElementException | StaleElementReferenceException ignored) {
                sleep(500);
            }
        }
        return false;
    }

    private boolean isTimeout(long startTime, long desiredTimeoutInSeconds) {
        long start = startTime / 1000;
        return System.currentTimeMillis() / 1000 > start + desiredTimeoutInSeconds;
    }
}
