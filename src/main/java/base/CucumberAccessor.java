package base;

import cucumber.api.PickleStepTestStep;
import cucumber.api.Result;
import cucumber.api.Scenario;
import gherkin.pickles.PickleTable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public class CucumberAccessor {

    public static String getError(Scenario scenario) throws NoSuchFieldException, IllegalAccessException {
        Field fieldStepResults = scenario.getClass().getDeclaredField("stepResults");
        fieldStepResults.setAccessible(true);
        ArrayList<Result> results = (ArrayList<Result>) fieldStepResults.get(scenario);
        for (Result result : results) {
            if (result.getError() != null) {
                return result.getErrorMessage() + "\n" + Arrays.toString(result.getError().getStackTrace());
            }
        }
        return "";
    }

    public static String getDataTableFromStep(PickleStepTestStep currentStepDef) {
        if (!currentStepDef.getStepArgument().isEmpty()) {
            if (currentStepDef.getStepArgument().get(0) instanceof PickleTable) {
                PickleTable pt = (PickleTable) currentStepDef.getStepArgument().get(0);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("<br /> <br />");
                pt.getRows().forEach(pickleRow -> {
                    stringBuilder.append("| ");
                    pickleRow.getCells().forEach(pickleCell -> {
                        stringBuilder.append(pickleCell.getValue());
                        stringBuilder.append(" | ");
                    });
                    stringBuilder.append("<br />");
                });
                return stringBuilder.toString();
            }
        }
        return "";
    }
}
