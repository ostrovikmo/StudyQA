package tests.duckController;

import clients.duckActionsClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class Delete extends duckActionsClient {

    @Test(description = "Проверка, что уточка с существующим id удалена")
    @CitrusTest
    public void successfulDelete(@Optional @CitrusResource TestCaseRunner runner) {
        createDuck(runner, "yellow", 0.15, "rubber", "quack", "FIXED");
        getDuckId(runner);
        duckDelete(runner, "${duckId}");
        validateResponse(runner, "{\n" + "  \"message\": \"Duck is deleted\"\n" + "}");
    }
}