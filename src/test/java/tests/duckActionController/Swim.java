package tests.duckActionController;

import clients.DuckActionsClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import static com.consol.citrus.container.FinallySequence.Builder.doFinally;

@Epic("Тесты на duck-action-controller")
@Feature("эндпоинт /api/duck/action/swim")

public class Swim  extends DuckActionsClient {

   @Test(description = "Проверка того, что уточка поплыла")
   @CitrusTest
    public void successfulSwim(@Optional @CitrusResource TestCaseRunner runner) {
       runner.variable("duckId","1234567");
       runner.$(doFinally().actions(context ->
               databaseUpdate(runner, "DELETE FROM DUCK WHERE ID=${duckId}")));
       databaseUpdate(runner,
               "insert into DUCK (id, color, height, material, sound, wings_state)\n" +
                       "values (${duckId}, 'red', 3.0, 'rubber', 'quack','ACTIVE');");
       duckSwim(runner,"${duckId}");
       validateResponse(runner, "duckActionController/successfulSwim.json");
   }
}