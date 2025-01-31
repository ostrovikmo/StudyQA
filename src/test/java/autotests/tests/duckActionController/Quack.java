package autotests.tests.duckActionController;

import autotests.clients.DuckActionsClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;
import autotests.payloads.Duck;
import autotests.payloads.WingState;

public class Quack extends DuckActionsClient {

    @Test(description = "Проверка, что уточка с существующим id крякает")
    @CitrusTest
    public void successfulQuack(@Optional @CitrusResource TestCaseRunner runner) {
        Duck duck = new Duck().color("yellow").height(0.15).material("rubber").sound("quack").wingsState(WingState.FIXED);
        createDuck(runner, duck);
        getDuckId(runner);
        duckQuack(runner, "${duckId}", 1, 1);
        validateResponse(runner, "duckActionController/successfulQuack.json");
    }
}