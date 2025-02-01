package autotests.tests.duckController;

import autotests.clients.DuckActionsClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.testng.CitrusParameters;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

@Epic("Тесты на duck-controller")
@Feature("эндпоинт /api/duck/create")

public class Create extends DuckActionsClient {


    //    параметризированные тесты
    duckUpdate duckProperties1 = new duckUpdate()
            .color("yellow")
            .height(0.05)
            .material("rubber")
            .sound("quack")
            .wingsState("ACTIVE");
    duckUpdate duckProperties2 = new duckUpdate()
            .color("green")
            .height(1.0)
            .material("plastic")
            .sound("meow")
            .wingsState("FIXED");
    duckUpdate duckProperties3 = new duckUpdate()
            .color("red")
            .height(17.0)
            .material("steel")
            .sound("ahoy")
            .wingsState("ACTIVE");
    duckUpdate duckProperties4 = new duckUpdate()
            .color("blue")
            .height(10.0)
            .material("rubber")
            .sound("crya")
            .wingsState("FIXED");
    duckUpdate duckProperties5 = new duckUpdate()
            .color("black")
            .height(5.0)
            .material("paper")
            .sound("hello")
            .wingsState("ACTIVE");


    @Test(dataProvider = "duckList")
    @CitrusTest
    @CitrusParameters({"payload", "response", "runner"})
    public void successfulDuckCreate(Object payload, String response, @Optional @CitrusResource TestCaseRunner runner) {
        createDuck(runner, payload);
        validateResponse(runner, response);
    }
    @DataProvider(name = "duckList")
    public Object[][] DuckProvider() {
        return new Object[][] {
                {duckProperties1, "getDuckPropertiesTest/duckYellowProperties.json", null},
                {duckProperties2, "getDuckPropertiesTest/duckGreenProperties.json", null},
                {duckProperties3, "getDuckPropertiesTest/duckRedProperties.json", null},
                {duckProperties4, "getDuckPropertiesTest/duckBlueProperties.json", null},
                {duckProperties5, "getDuckPropertiesTest/duckBlackProperties.json", null}
        };
    }

    @Test (description = "Создание утки из резины")
    @CitrusTest
    public void successfulCreateRubber(@Optional @CitrusResource TestCaseRunner runner) {
        createDuck(runner, "yellow", "0.15", "rubber", "quack", "ACTIVE");
        getDuckId(runner);
        validateDuckInDatabase(runner, "${duckId}", "yellow", "0.15", "rubber", "quack", "ACTIVE");
    }


    @Test (description = "Создание утки из дерева")
    @CitrusTest
    public void successfulCreateWood(@Optional @CitrusResource TestCaseRunner runner) {
        createDuck(runner, "yellow", "0.15", "wood", "quack", "ACTIVE");
        getDuckId(runner);
        validateDuckInDatabase(runner, "${duckId}", "yellow", "0.15", "wood", "quack", "ACTIVE");
    }
}
