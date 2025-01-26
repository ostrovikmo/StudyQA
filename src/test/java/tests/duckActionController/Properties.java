package tests.duckActionController;

import clients.duckActionsClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;
import payloads.Duck;
import payloads.WingState;

public class Properties extends duckActionsClient {

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

