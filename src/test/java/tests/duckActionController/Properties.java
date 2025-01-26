package tests.duckActionController;

import clients.duckActionsClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class Properties extends duckActionsClient {

    @Test(description = "Проверка какие свойства имеет утка")
    @CitrusTest
    public void successfulGetProprties(@Optional @CitrusResource TestCaseRunner runner) {
        createDuck(runner, "yellow", 0.15, "rubber", "quack", "FIXED");
        getDuckId(runner);
        duckProperties(runner, "${duckId}");
        validateResponse(runner, "{\n" + "  \"message\": \"\"color\": \"yellow\",\n" +
                "  \"height\": 15,\n" +
                "  \"material\": \"rubber\",\n" +
                "  \"sound\": \"quack\",\n" +
                "  \"wingsState\": \"FIXED\"\"\n" + "}");
    }
}

