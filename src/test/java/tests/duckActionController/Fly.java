package tests.duckActionController;

import clients.duckActionsClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;


public class Fly extends duckActionsClient {

    @Test(description = "Проверка, что уточка с существующим id c активными крыльями летит")
    @CitrusTest
    public void successfulFlyActive(@Optional @CitrusResource TestCaseRunner runner) {
        createDuck(runner, "yellow", 0.15, "rubber", "quack", "ACTIVE");
        getDuckId(runner);
        duckFly(runner, "${duckId}");
        validateResponse(runner, "{\n" + "  \"message\": \"I'm flying\"\n" + "}");
    }

    @Test(description = "Проверка, что уточка с существующим id cо связанными крыльями не летит")
    @CitrusTest
    public void FlyFixed(@Optional @CitrusResource TestCaseRunner runner) {
        createDuck(runner, "yellow", 0.15, "rubber", "quack", "FIXED");
        getDuckId(runner);
        duckFly(runner, "${duckId}");
        validateResponse(runner, "{\n" + "  \"message\": \"I'm not flying\"\n" + "}");
    }

    @Test(description = "Проверка, что уточка с существующим id c крыльями в неопределенном состоянии не летит")
    @CitrusTest
    public void FlyUndefined(@Optional @CitrusResource TestCaseRunner runner) {
        createDuck(runner, "yellow", 0.15, "rubber", "quack", "UNDEFINED");
        getDuckId(runner);
        duckFly(runner, "${duckId}");
        validateResponse(runner, "{\n" + "  \"message\": \"I'm not flying\"\n" + "}");
    }
}

