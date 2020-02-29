package step_definitions.step_hooks;

import base.ExtentReporter;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class StepHooks {

    private WebDriver driver;
    private static boolean isRunBefore = false;

    @Before( order = -1)
    public void beforeAllSelenium() throws Exception {
        if (!isRunBefore) {
            isRunBefore = true;
            ExtentReporter.initializeReporter();
        }
    }

    @Before()
    public void startReporter(Scenario scenario) {
        ExtentReporter.startTest(scenario.getName());
    }

    @Before()
    public void launch() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--window-size=1920,1200");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterStep()
    public void afterStep(Scenario scenario) throws NoSuchFieldException, IllegalAccessException {
        ExtentReporter.logStep(scenario);
    }

    @After()
    public void teardownSelenium(Scenario scenario) throws ClassNotFoundException, IOException, NoSuchFieldException, IllegalAccessException {
        if (driver != null) {
            if (scenario.isFailed()) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            }
            ExtentReporter.setStatusWithScreenshot(ExtentReporter.getTest(),scenario,driver);
            driver.quit();
        }
        ExtentReporter.saveReport();
    }

    public WebDriver getDriver() {
        return driver;
    }

}
