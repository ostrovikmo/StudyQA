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
@Feature("эндпоинт /api/duck/action/properties")

public class Properties extends DuckActionsClient {

    @Test(description = "Проверка какие свойства имеет утка")
    @CitrusTest
    public void successfulGetProprties(@Optional @CitrusResource TestCaseRunner runner) {
        Duck duck = new Duck().color("yellow").height(0.15).material("rubber").sound("quack").wingsState(WingState.FIXED);
        duckCreate(runner, duck);
        getDuckId(runner);
        duckProperties(runner, "${duckId}");
        validateResponse(runner, "duckActionController/properties.json");
    }
}

