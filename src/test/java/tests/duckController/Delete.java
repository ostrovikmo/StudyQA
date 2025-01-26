package tests.duckController;

import clients.duckActionsClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;
import payloads.Duck;
import payloads.WingState;

public class Delete extends duckActionsClient {

    @Test(description = "Проверка, что уточка с существующим id удалена")
    @CitrusTest
    public void successfulDelete(@Optional @CitrusResource TestCaseRunner runner) {
        Duck duck = new Duck().color("yellow").height(0.15).material("rubber").sound("quack").wingsState(WingState.FIXED);
        createDuck(runner, duck);
        getDuckId(runner);
        duckDelete(runner, "${duckId}");
        validateResponse(runner, "duckController/successfulDelete.json");
    }
}