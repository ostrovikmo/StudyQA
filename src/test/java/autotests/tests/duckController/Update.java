package autotests.tests.duckController;

import autotests.clients.DuckActionsClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;
import payloads.Duck;
import payloads.WingState;

@Epic("Тесты на duck-controller")
@Feature("эндпоинт /api/duck/update")
public class Update extends DuckActionsClient {

        @Test(description = "Проверка, что у уточки с существующим id были изменены цвет и высота")
        @CitrusTest
        public void successfulUpdateColorHeight(@Optional @CitrusResource TestCaseRunner runner) {
            Duck duck = new Duck().color("yellow").height(0.15).material("rubber").sound("quack").wingsState(WingState.FIXED);
            createDuck(runner, duck);
            getDuckId(runner);
            duckUpdate(runner, "${duckId}","red",0.17, "rubber","quack","FIXED");
            validateResponse(runner, "duckController/successfulUpdate.json");
        }

    @Test(description = "Проверка, что у уточки с существующим id были изменены цвет и звук")
    @CitrusTest
    public void successfulUpdateColorSound(@Optional @CitrusResource TestCaseRunner runner) {
        Duck duck = new Duck().color("yellow").height(0.15).material("rubber").sound("quack").wingsState(WingState.FIXED);
        createDuck(runner, duck);
        getDuckId(runner);
        duckUpdate(runner, "${duckId}","blue",0.15, "rubber","ahoy","FIXED");
        validateResponse(runner, "duckController/successfulUpdate.json");
    }
    }

