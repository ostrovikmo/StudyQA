package clients;

import autotests.EndpointConfig;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.testng.TestNG;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;

@ContextConfiguration(classes= {EndpointConfig.class})
public class DuckActionsClient extends TestNGCitrusSpringSupport {

    @Autowired
    protected HttpClient DuckService;



    //Создание утки
    public void createDuck(TestCaseRunner runner, String color, double height, String material, String sound, String wingsState) {
        runner.$(http()
                .client(DuckService)
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

    //Валидация ответа
    public void validateResponse(TestCaseRunner runner, String responseMessage) {
        runner.$(http()
                .client(DuckService)
                .receive()
                .response(HttpStatus.OK)
                .message()
                .contentType(MediaType.APPLICATION_JSON_VALUE).body(responseMessage));
    }

    //Плыви утка
    public void duckSwim(TestCaseRunner runner, String id) {
        runner.$(http().client(DuckService)
                .send()
                .get("/api/duck/action/swim")
                .queryParam("id", id));
    }

    //Лети  утка
    public void duckFly(TestCaseRunner runner, String id) {
        runner.$(http().client(DuckService)
                .send()
                .get("/api/duck/action/fly")
                .queryParam("id", id));
    }

    //Крякай утка
    public void duckQuack(TestCaseRunner runner, String id, double repetitionCount, double soundCount) {
        runner.$(http().client(DuckService)
                .send()
                .get("/api/duck/action/quack")
                .queryParam("id", id));
    }

    //Свойства утки
    public void duckProperties(TestCaseRunner runner, String id) {
        runner.$(http().client(DuckService)
                .send()
                .get("/api/duck/action/properties")
                .queryParam("id", id));
    }

    //вызов созданной утки по id
    public void Duck(TestCaseRunner runner, String id) {
        runner.$(http().client(DuckService)
                .send()
                .post("/api/duck/create")
                .queryParam("id", id));
    }

    //удалить утку
    public void duckDelete(TestCaseRunner runner, String id) {
        runner.$(http().client(DuckService)
                .send()
                .delete("/api/duck/action/delete")
                .queryParam("id", id));
    }
}
