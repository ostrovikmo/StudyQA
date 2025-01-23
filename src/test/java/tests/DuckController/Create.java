package tests.DuckController;

import clients.DuckActionsClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import static com.consol.citrus.dsl.MessageSupport.MessageBodySupport.fromBody;
import static com.consol.citrus.http.actions.HttpActionBuilder.http;

public class Create extends DuckActionsClient {

    @Test(description = "Создание утки из резины")
    @CitrusTest
    public void successfulCreateRubber (@Optional @CitrusResource TestCaseRunner runner)  {
            createDuck(runner, "yellow", 0.15, "rubber", "quack", "FIXED");
            runner.$(http().client("http://localhost:2222")
                    .receive()
                    .response(HttpStatus.OK)
                    .message()
                    .extract(fromBody().expression("$.id", "duckId")));
        Duck(runner, "${duckId}");
            validateResponse(runner, "{\n" + "  \"message\": \"Created\"\n" + "}");
        }

    @Test(description = "Создание утки из дерева")
    @CitrusTest
    public void successfulCreateWood (@Optional @CitrusResource TestCaseRunner runner)  {
        createDuck(runner, "yellow", 0.15, "wood", "quack", "FIXED");
        runner.$(http().client("http://localhost:2222")
                .receive()
                .response(HttpStatus.OK)
                .message()
                .extract(fromBody().expression("$.id", "duckId")));
        Duck(runner, "${duckId}");
        validateResponse(runner, "{\n" + "  \"message\": \"Created\"\n" + "}");
    }






    }


