package autotests.clients;

import autotests.BaseTest;
import autotests.EndpointConfig;
import com.consol.citrus.TestCaseRunner;
<<<<<<< Updated upstream:src/test/java/clients/DuckActionsClient.java
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.builder.ObjectMappingPayloadBuilder;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import com.fasterxml.jackson.databind.ObjectMapper;
=======
import io.qameta.allure.Step;
>>>>>>> Stashed changes:src/test/java/autotests/clients/DuckActionsClient.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
<<<<<<< Updated upstream:src/test/java/clients/DuckActionsClient.java
import org.springframework.http.MediaType;
=======
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
>>>>>>> Stashed changes:src/test/java/autotests/clients/DuckActionsClient.java
import org.springframework.test.context.ContextConfiguration;

import static com.consol.citrus.dsl.MessageSupport.MessageBodySupport.fromBody;
import static com.consol.citrus.http.actions.HttpActionBuilder.http;

<<<<<<< Updated upstream:src/test/java/clients/DuckActionsClient.java
@ContextConfiguration(classes= {EndpointConfig.class})

public class DuckActionsClient extends TestNGCitrusSpringSupport {
    @Autowired
    protected HttpClient DuckService;

     public void validateResponse(TestCaseRunner runner, String responseMessage) {
         runner.$(http().client(DuckService)
                 .receive()
                .response(HttpStatus.OK)
                .message()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new ClassPathResource(responseMessage)));
    }
=======

@ContextConfiguration(classes= {EndpointConfig.class})

public class DuckActionsClient extends BaseTest {

    @Autowired
    protected SingleConnectionDataSource testDb;

    public void databaseUpdate(TestCaseRunner runner, String sql) {
        runner.$(sql(testDb)
                .statement(sql));
    }

//    @Autowired
//    protected HttpClient DuckService;


//   Валидация значений в БД
protected void validateDuckInDatabase(TestCaseRunner runner, String id, String color, String height,
                                      String material, String sound, String wingsState) {
    runner.$(query(testDb)
            .statement("SELECT * FROM DUCK WHERE ID=" + id)
            .validate("COLOR",color)
            .validate("HEIGHT",height)
            .validate("MATERIAL",material)
            .validate("SOUND",sound)
            .validate("WINGS_STATE",wingsState));
}
>>>>>>> Stashed changes:src/test/java/autotests/clients/DuckActionsClient.java

    //получение ID утки
    public void getDuckId(TestCaseRunner runner) {
        runner.$(http()
                .client(DuckService)
                .receive()
                .response(HttpStatus.OK)
                .message()
                .extract(fromBody().expression("$.id", "duckId")));
    }

    //новый вариант создания утки
    public void createDuck(TestCaseRunner runner, Object body) {
            runner.$(http().client(DuckService)
                    .send()
                    .post("/api/duck/create")
                    .message()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(new ObjectMappingPayloadBuilder(body, new ObjectMapper())));
        }

    //обновление свойств утки
    public void duckUpdate(TestCaseRunner runner,Object body) {
        runner.$(http().client(DuckService)
                .send()
                .put("/api/duck/update")
                .message()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new ObjectMappingPayloadBuilder(body, new ObjectMapper())));
    }

<<<<<<< Updated upstream:src/test/java/clients/DuckActionsClient.java
    //Плыви утка
    public void duckSwim(TestCaseRunner runner, String id) {
        runner.$(http()
                .client(DuckService)
                .send()
                .get("/api/duck/action/swim")
                .queryParam("id", id));
    }

    //Лети  утка
    public void duckFly(TestCaseRunner runner, String id) {
        runner.$(http()
                .client(DuckService)
                .send()
                .get("/api/duck/action/fly")
                .queryParam("id", id));
=======
    @Step("Эндпоинт для обновления свойств утки")
    public void duckUpdate(TestCaseRunner runner, String id) {
        sendPutRequest(runner, DuckService,
                "/api/duck/update","id", id, "color", color, "height", height,
                "material", material, "sound", sound, "wingsState", wingsState);
>>>>>>> Stashed changes:src/test/java/autotests/clients/DuckActionsClient.java
    }

    //Крякай утка
    public void duckQuack(TestCaseRunner runner, String id, int repetitionCount, int soundCount) {
<<<<<<< Updated upstream:src/test/java/clients/DuckActionsClient.java
        runner.$(http()
                .client(DuckService)
                .send()
                .get("/api/duck/action/quack")
                .queryParam("id", id));
    }

    //Свойства утки
    public void duckProperties(TestCaseRunner runner, String id) {
        runner.$(http()
                .client(DuckService)
                .send()
                .get("/api/duck/action/properties")
                .queryParam("id", id));
=======
        sendGetRequestQuack(runner, DuckService,
                "/api/duck/action/quack", "id", id, "repetitionCount",repetitionCount, "soundCount",soundCount);
    }

    @Step("Эндпоинт для плавания уточки")
    public void duckSwim(TestCaseRunner runner, String id) {
        sendGetRequest(runner, DuckService,
                "/api/duck/action/swim", "id", id);
     }

        @Step("Эндпоинт для полета уточки")
        public void duckFly(TestCaseRunner runner, String id) {
            sendGetRequest(runner,
                    DuckService,
                    "/api/duck/action/fly",
                    "id", id);
        }

    @Step("Эндпоинт для просмотра свойств уточки")
    public void duckProperties(TestCaseRunner runner, String id) {
        sendGetRequest(runner, DuckService,
                "/api/duck/action/properties", "id", id);
>>>>>>> Stashed changes:src/test/java/autotests/clients/DuckActionsClient.java
    }

    //вызов созданной утки по id
    public void getDuck(TestCaseRunner runner, String id) {
        runner.$(http()
                .client(DuckService)
                .send()
                .post("/api/duck/create")
                .queryParam("id", id));
    }

    //удалить утку
    public void duckDelete(TestCaseRunner runner, String id) {
<<<<<<< Updated upstream:src/test/java/clients/DuckActionsClient.java
        runner.$(http()
                .client(DuckService)
                .send()
                .delete("/api/duck/action/delete")
                .queryParam("id", id));
=======
        sendDeleteRequest(runner, DuckService,
                "/api/duck/action/delete", "id",id);
>>>>>>> Stashed changes:src/test/java/autotests/clients/DuckActionsClient.java
    }
}