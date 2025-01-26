package tests.duckActionController;

import clients.duckActionsClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class Quack extends duckActionsClient {

    @Test(description = "Проверка, что уточка с существующим id крякает")
    @CitrusTest
    public void successfulQuack(@Optional @CitrusResource TestCaseRunner runner) {
        createDuck(runner, "yellow", 0.15, "rubber", "quack", "FIXED");
        getDuckId(runner);
        duckQuack(runner, "${duckId}", 1, 1);
        validateResponse(runner, "{\n" + "  \"message\": \"Quack\"\n" + "}");
    }
}

