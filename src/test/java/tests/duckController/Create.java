package tests.duckController;

import clients.DuckActionsClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;
import payloads.Duck;
import payloads.WingState;

public class Create extends DuckActionsClient {

    @Test(description = "Создание утки из резины")
    @CitrusTest
    public void successfulCreateRubber (@Optional @CitrusResource TestCaseRunner runner)  {
        Duck duck = new Duck().color("yellow").height(0.15).material("rubber").sound("quack").wingsState(WingState.FIXED);
        createDuck(runner, duck);
            getDuckId(runner);
        getDuck(runner, "${duckId}");
            validateResponse(runner, "duckController/successfulCreate.json");
        }

    @Test(description = "Создание утки из дерева")
    @CitrusTest
    public void successfulCreateWood (@Optional @CitrusResource TestCaseRunner runner)  {
        Duck duck = new Duck().color("yellow").height(0.15).material("wood").sound("quack").wingsState(WingState.FIXED);
        createDuck(runner, duck);
        getDuckId(runner);
        getDuck(runner, "${duckId}");
        validateResponse(runner, "duckController/successfulCreate.json");
    }
}


