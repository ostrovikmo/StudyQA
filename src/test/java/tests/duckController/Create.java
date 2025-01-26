package tests.duckController;

import clients.duckActionsClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class Create extends duckActionsClient {

    @Test(description = "Создание утки из резины")
    @CitrusTest
    public void successfulCreateRubber (@Optional @CitrusResource TestCaseRunner runner)  {
            createDuck(runner, "yellow", 0.15, "rubber", "quack", "FIXED");
            getDuckId(runner);
        Duck(runner, "${duckId}");
            validateResponse(runner, "{\n" + "  \"message\": \"Created\"\n" + "}");
        }

    @Test(description = "Создание утки из дерева")
    @CitrusTest
    public void successfulCreateWood (@Optional @CitrusResource TestCaseRunner runner)  {
        createDuck(runner, "yellow", 0.15, "wood", "quack", "FIXED");
        getDuckId(runner);
        Duck(runner, "${duckId}");
        validateResponse(runner, "{\n" + "  \"message\": \"Created\"\n" + "}");
    }
}


