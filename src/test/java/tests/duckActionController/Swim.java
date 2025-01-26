package tests.duckActionController;

import clients.duckActionsClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class Swim  extends duckActionsClient {

    @Test(description = "Проверка, что уточка с существующим id поплыла")
    @CitrusTest
    public void successfulSwim(@Optional @CitrusResource TestCaseRunner runner) {
        createDuck(runner, "yellow", 0.15, "rubber", "quack", "FIXED");
        getDuckId(runner);
        duckSwim(runner, "${duckId}");
        validateResponse(runner, "{\n" + "  \"message\": \"I'm swimming\"\n" + "}");
    }
}