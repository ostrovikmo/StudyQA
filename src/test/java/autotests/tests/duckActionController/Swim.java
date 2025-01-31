package autotests.tests.duckActionController;

<<<<<<< Updated upstream:src/test/java/tests/duckActionController/Swim.java
import clients.DuckActionsClient;
import payloads.Duck;
import payloads.WingState;
=======
import autotests.clients.DuckActionsClient;
>>>>>>> Stashed changes:src/test/java/autotests/tests/duckActionController/Swim.java
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

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