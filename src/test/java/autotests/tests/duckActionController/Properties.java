package autotests.tests.duckActionController;

import autotests.clients.DuckActionsClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;
import autotests.payloads.Duck;
import autotests.payloads.WingState;

@Epic("Тесты на duck-action-controller")
@Feature("эндпоинт /api/duck/action/properties")
public class Properties extends DuckActionsClient {

    @Test(description = "Проверка какие свойства имеет утка")
    @CitrusTest
    public void successfulGetProprties(@Optional @CitrusResource TestCaseRunner runner) {
        Duck duck = new Duck().color("yellow").height(0.15).material("rubber").sound("quack").wingsState(WingState.FIXED);
        createDuck(runner, duck);
        getDuckId(runner);
        duckProperties(runner, "${duckId}");
        validateResponse(runner, "duckActionController/properties.json");
    }
}

