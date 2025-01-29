package clients;

import autotests.EndpointConfig;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.builder.ObjectMappingPayloadBuilder;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;

import static com.consol.citrus.dsl.MessageSupport.MessageBodySupport.fromBody;
import static com.consol.citrus.http.actions.HttpActionBuilder.http;

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
    }

    //Крякай утка
    public void duckQuack(TestCaseRunner runner, String id, int repetitionCount, int soundCount) {
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
        runner.$(http()
                .client(DuckService)
                .send()
                .delete("/api/duck/action/delete")
                .queryParam("id", id));
    }
}