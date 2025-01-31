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
@Feature("эндпоинт /api/duck/action/fly")


public class Fly extends DuckActionsClient {

    @Test(description = "Проверка, что уточка с существующим id c активными крыльями летит")
    @CitrusTest
    public void successfulFlyActive(@Optional @CitrusResource TestCaseRunner runner) {
        Duck duck = new Duck().color("yellow").height(0.15).material("rubber").sound("quack").wingsState(WingState.ACTIVE);
        duckCreate(runner, duck);
        getDuckId(runner);
        duckFly(runner, "${duckId}");
        validateResponse(runner, "duckActionController/successfulFlyActive.json");
    }

    @Test(description = "Проверка, что уточка с существующим id cо связанными крыльями не летит")
    @CitrusTest
    public void FlyFixed(@Optional @CitrusResource TestCaseRunner runner) {
        Duck duck = new Duck().color("yellow").height(0.15).material("rubber").sound("quack").wingsState(WingState.FIXED);
        duckCreate(runner, duck);
        getDuckId(runner);
        duckFly(runner, "${duckId}");
        validateResponse(runner, "duckActionController/notFly.json");
    }

    @Test(description = "Проверка, что уточка с существующим id c крыльями в неопределенном состоянии не летит")
    @CitrusTest
    public void FlyUndefined(@Optional @CitrusResource TestCaseRunner runner) {
        Duck duck = new Duck().color("yellow").height(0.15).material("rubber").sound("quack").wingsState(WingState.UNDEFINED);
        duckCreate(runner, duck);
        getDuckId(runner);
        duckFly(runner, "${duckId}");
        validateResponse(runner, "duckActionController/notFly.json");
    }
}

