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
@Feature("эндпоинт /api/duck/delete")

public class Delete extends DuckActionsClient {

    @Test(description = "Проверка, что уточка с существующим id удалена")
    @CitrusTest
    public void successfulDelete(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "yellow", "0.15", "wood", "quack", "ACTIVE");
        getDuckId(runner);
        duckDelete(runner, "${duckId}");
        validateResponse(runner, "duckController/successfulDelete.json");
    }
}