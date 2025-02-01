package autotests.tests.duckActionController;

import autotests.payloads.Duck;
import autotests.payloads.WingState;

import autotests.clients.DuckActionsClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

@Epic("Тесты на duck-action-controller")
@Feature("эндпоинт /api/duck/action/swim")

public class Swim  extends DuckActionsClient {

    @Test(description = "Проверка того, что уточка поплыла")
    @CitrusTest
    public void successfulSwim(@Optional @CitrusResource TestCaseRunner runner) {
        Duck duck = new Duck().color("yellow").height(0.15).material("rubber").sound("quack").wingsState(WingState.FIXED);
        createDuck(runner, duck);
        getDuckId(runner);
        duckSwim(runner, "${duckId}");
        validateResponse(runner, "duckActionController/successfulSwim.json");
    }

//    @Test(description = "Проверка, что уточка с существующим id поплыла")
//    @CitrusTest
//    public void successfulSwim(@Optional @CitrusResource TestCaseRunner runner) {
//        createDuck(runner, "yellow", 0.15, "rubber", "quack", "FIXED");
//        getDuckId(runner);
//        duckSwim(runner, "${duckId}");
//        validateResponse(runner, "{\n" + "  \"message\": \"I'm swimming\"\n" + "}");
//    }
}