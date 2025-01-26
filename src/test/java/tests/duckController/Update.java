package tests.duckController;

import clients.duckActionsClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class Update extends duckActionsClient {

        @Test(description = "Проверка, что у уточки с существующим id были изменены цвет и высота")
        @CitrusTest
        public void successfulUpdateColorHeight(@Optional @CitrusResource TestCaseRunner runner) {
            createDuck(runner, "yellow", 0.15, "rubber", "quack", "FIXED");
            getDuckId(runner);
            duckUpdate(runner, "${duckId}","red",0.17, "rubber","quack","FIXED");
            validateResponse(runner, "{\n" + "  \"message\": \"Duck with id = \"${duckId}\" is updated \"\n" + "}");
        }

    @Test(description = "Проверка, что у уточки с существующим id были изменены цвет и звук")
    @CitrusTest
    public void successfulUpdateColorSound(@Optional @CitrusResource TestCaseRunner runner) {
        createDuck(runner, "yellow", 0.15, "rubber", "quack", "FIXED");
        getDuckId(runner);
        duckUpdate(runner, "${duckId}","blue",0.15, "rubber","ahoy","FIXED");
        validateResponse(runner, "{\n" + "  \"message\": \"Duck with id = \"${duckId}\" is updated \"\n" + "}");
    }
    }

