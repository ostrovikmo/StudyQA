package autotests.DuckController;

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

public class Create extends TestNGCitrusSpringSupport {

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


    //утка
    public void Duck(TestCaseRunner runner, String id) {
        runner.$(http().client("http://localhost:2222")
                .send()
                .post("/api/duck/create")
                .queryParam("id", id));
    }
    //Валидация ответа
    public void validateResponse(TestCaseRunner runner, String responseMessage) {
        runner.$(http().client("http://localhost:2222")
                .receive()
                .response(HttpStatus.OK)
                .message()
                .contentType(MediaType.APPLICATION_JSON_VALUE).body(responseMessage));
    }


            //Создание утки
        public void createDuck(TestCaseRunner runner, String color, double height, String material, String sound, String wingsState) {
            runner.$(http().client("http://localhost:2222")
                    .send()
                    .post("/api/duck/create")
                    .message()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body("{\n" + "  \"color\": \"" + color + "\",\n"
                            + "  \"height\": " + height + ",\n"
                            + "  \"material\": \"" + material + "\",\n"
                            + "  \"sound\": \"" + sound + "\",\n"
                            + "  \"wingsState\": \"" + wingsState
                            + "\"\n" + "}"));
        }
    }


