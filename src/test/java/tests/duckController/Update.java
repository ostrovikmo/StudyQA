package tests.duckController;

import clients.DuckActionsClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

@Epic("Тесты на duck-controller")
@Feature("эндпоинт /api/duck/update")

public class Update extends DuckActionsClient {

        @Test(description = "Проверка, что у уточки с существующим id были изменены цвет и высота")
        @CitrusTest
        public void successfulUpdateColorHeight(@Optional @CitrusResource TestCaseRunner runner) {
            duckCreate(runner, "yellow", "0.15", "rubber", "quack", "ACTIVE");
            getDuckId(runner);
            duckUpdate(runner, "${duckId}","red",0.17, "rubber","quack","ACTIVE");
            validateDuckInDatabase(runner, "${duckId}", "red", "0.17", "rubber", "quack", "ACTIVE");
        }


    @Test(description = "Проверка, что у уточки с существующим id были изменены цвет и звук")
    @CitrusTest
    public void successfulUpdateColorSound(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "yellow", "0.15", "rubber", "quack", "ACTIVE");
        getDuckId(runner);
        duckUpdate(runner, "${duckId}","blue",0.15, "rubber","ahoy","ACTIVE");
        validateDuckInDatabase(runner, "${duckId}", "blue", "0.15", "rubber", "ahoy", "ACTIVE");
    }
    }

