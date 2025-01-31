package tests.duckActionController;

import clients.DuckActionsClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;
import payloads.Duck;
import payloads.WingState;

@Epic("Тесты на duck-action-controller")
@Feature("эндпоинт /api/duck/action/quack")

public class Quack extends DuckActionsClient {

    @Test(description = "Проверка, что уточка с существующим id крякает")
    @CitrusTest
    public void successfulQuack(@Optional @CitrusResource TestCaseRunner runner) {
        Duck duck = new Duck().color("yellow").height(0.15).material("rubber").sound("quack").wingsState(WingState.FIXED);
        duckCreate(runner, duck);
        getDuckId(runner);
        duckQuack(runner, "${duckId}", 1, 1);
        validateResponse(runner, "duckActionController/successfulQuack.json");
    }
}