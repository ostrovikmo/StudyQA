package clients;

import autotests.EndpointConfig;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import io.qameta.allure.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.context.ContextConfiguration;


import static com.consol.citrus.actions.ExecuteSQLAction.Builder.sql;
import static com.consol.citrus.actions.ExecuteSQLQueryAction.Builder.query;
import static com.consol.citrus.dsl.MessageSupport.MessageBodySupport.fromBody;
import static com.consol.citrus.http.actions.HttpActionBuilder.http;




@ContextConfiguration(classes= {EndpointConfig.class})

public class DuckActionsClient extends TestNGCitrusSpringSupport {

    @Autowired
    protected SingleConnectionDataSource testDb;

    public void databaseUpdate(TestCaseRunner runner, String sql) {
        runner.$(sql(testDb)
                .statement(sql));
    }


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

    //получение ID утки
    public void getDuckId(TestCaseRunner runner) {
        runner.$(http()
                .client(DuckService)
                .receive()
                .response(HttpStatus.OK)
                .message()
                .extract(fromBody().expression("$.id", "duckId")));
    }

    //создание утки
    @Step("Эндпоинт для создания уточки")
    public void duckCreate(TestCaseRunner runner, Object
            payload) {
        sendPostRequest(runner, DuckService,
                "/api/duck/create", payload);
    }

    @Step("Эндпоинт для обновление свойств утки")
    public void duckUpdate(TestCaseRunner runner, String id) {
        sendPostRequest(runner, DuckService,
                "/api/duck/update",id);
    }

    @Step("Эндпоинт для плавания уточки")
    public void duckSwim(TestCaseRunner runner, String id) {
        sendPostRequest(runner, DuckService,
                "/api/duck/action/swim", id);
     }

        @Step("Эндпоинт для полета уточки")
    public void duckFly(TestCaseRunner runner, String id) {
            sendPostRequest(runner, DuckService,
                    "/api/duck/action/fly", id);
    }

    @Step("Эндпоинт для кряканья уточки")
    public void duckQuack(TestCaseRunner runner, String id, int repetitionCount, int soundCount) {
        sendPostRequest(runner, DuckService,
                "/api/duck/action/quack", id, repetitionCount,soundCount);
    }

    @Step("Эндпоинт для просмотра свойств уточки")
    public void duckProperties(TestCaseRunner runner, String id) {
        sendPostRequest(runner, DuckService,
                "/api/duck/action/properties", id);
    }

    @Step("Эндпоинт для удаления уточки")
    public void duckDelete(TestCaseRunner runner, String id) {
        sendPostRequest(runner, DuckService,
                "/api/duck/action/delete", id);
    }
}