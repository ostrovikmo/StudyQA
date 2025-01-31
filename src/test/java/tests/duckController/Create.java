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
@Feature("эндпоинт /api/duck/create")

public class Create extends DuckActionsClient {

    @Test (description = "Создание утки из резины")
    @CitrusTest
    public void successfulCreateRubber(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "yellow", "0.15", "rubber", "quack", "ACTIVE");
        getDuckId(runner);
        validateDuckInDatabase(runner, "${duckId}", "yellow", "0.15", "rubber", "quack", "ACTIVE");
    }

    @Test (description = "Создание утки из дерева")
    @CitrusTest
    public void successfulCreateWood(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "yellow", "0.15", "wood", "quack", "ACTIVE");
        getDuckId(runner);
        validateDuckInDatabase(runner, "${duckId}", "yellow", "0.15", "wood", "quack", "ACTIVE");
    }
}


