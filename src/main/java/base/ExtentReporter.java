package base;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import cucumber.api.PickleStepTestStep;
import cucumber.api.Scenario;
import cucumber.api.TestCase;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExtentReporter {

    private static ExtentReports extentReporter;
    private static Map<Long, ExtentTest> testMap = new HashMap<>();
    private static int stepCount;

    public static synchronized void initializeReporter() {
        File reportDir = new File(System.getProperty("user.dir") + "/test-output");
        if (!reportDir.exists()) {
            reportDir.mkdir();
        }
        ExtentHtmlReporter html = new ExtentHtmlReporter(reportDir + "/extent-report.html");
        extentReporter = new ExtentReports();
        extentReporter.attachReporter(html);
    }


    public static synchronized void startTest(String testName) {
        stepCount = 0;
        ExtentTest test = extentReporter.createTest(testName);
        testMap.put(Thread.currentThread().getId(), test);
    }

    public static synchronized ExtentTest getTest() {
        return testMap.get(Thread.currentThread().getId());
    }

    public static synchronized void logStep(Scenario scenario) throws NoSuchFieldException, IllegalAccessException {
        String result = scenario.getStatus().lowerCaseName();
        if (result.equalsIgnoreCase("passed")) {
            getTest().log(Status.PASS, getStepName(scenario));
        } else if (result.equalsIgnoreCase("failed")) {
            getTest().log(Status.FAIL, getStepName(scenario));
        } else if (result.equalsIgnoreCase("skipped")) {
            getTest().log(Status.SKIP, getStepName(scenario));
        }
        stepCount++;
    }

    public static synchronized void saveReport() {
        extentReporter.flush();
    }

    public static synchronized void setStatus(ExtentTest test, Scenario scenario) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        String result = scenario.getStatus().lowerCaseName();
        if (result.equalsIgnoreCase("passed")) {
            test.pass("PASSED");
        } else if (result.equalsIgnoreCase("failed")) {
            test.fail(CucumberAccessor.getError(scenario));
        } else if (result.equalsIgnoreCase("skipped")) {
            test.log(Status.SKIP, "TEST SKIPPED");
            test.skip(CucumberAccessor.getError(scenario));
        }
    }

    public static synchronized void setStatusWithScreenshot(ExtentTest test, Scenario scenario, WebDriver driver) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException, IOException {
        String result = scenario.getStatus().lowerCaseName();
        if (result.equalsIgnoreCase("passed")) {
            test.pass("PASSED");
        } else if (result.equalsIgnoreCase("failed")) {
            MediaEntityModelProvider screenshotProvider =
                    MediaEntityBuilder.createScreenCaptureFromBase64String(((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64)).build();
            test.log(Status.FAIL, "Failure snapshot: ", screenshotProvider);
            test.fail(CucumberAccessor.getError(scenario));
        } else if (result.equalsIgnoreCase("skipped")) {
            test.log(Status.SKIP, "TEST SKIPPED");
            test.skip(CucumberAccessor.getError(scenario));
        }
    }


    private static synchronized String getStepName(Scenario scenario) throws NoSuchFieldException, IllegalAccessException {
        Field f = scenario.getClass().getDeclaredField("testCase");
        f.setAccessible(true);
        TestCase r = (TestCase) f.get(scenario);

        //You need to filter out before/after hooks
        List<PickleStepTestStep> stepDefs = r.getTestSteps()
                .stream()
                .filter(x -> x instanceof PickleStepTestStep)
                .map(x -> (PickleStepTestStep) x)
                .collect(Collectors.toList());

        //This object now holds the information about the current step definition
        PickleStepTestStep currentStepDef = stepDefs
                .get(stepCount);
        return currentStepDef.getStepText() + CucumberAccessor.getDataTableFromStep(currentStepDef);
    }
}
