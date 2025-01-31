package autotests;

import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.http.client.HttpClient;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;

@ContextConfiguration(classes = {EndpointConfig.class})
public class BaseTest {
    @Autowired
    protected HttpClient DuckService;

    //запрос на создание уточки
    protected void sendPostRequest(TestCaseRunner runner, HttpClient URL, String path, String queName, String queValue) {
        runner.$(http()
                .client(URL)
                .send()
                .post(path)
                .queryParam(queName, queValue));
    }

    //запрос на удаление уточки
    protected void sendDeleteRequest(TestCaseRunner runner, HttpClient URL, String path, String queName, String queValue) {
        runner.$(http()
                .client(URL)
                .send()
                .delete(path)
                .queryParam(queName, queValue));
    }

    //запрос на обновление свойств уточки
    protected void sendPutRequest(TestCaseRunner runner, HttpClient URL, String path, String queName, String queValue,
                                  String color, String colorValue, String height, String heightValue,
                                  String material, String materialValue,String sound, String soundValue, String wingsState, String wingsStateValue) {
        runner.$(http()
                .client(URL)
                .send()
                .put(path)
                .queryParam(queName, queValue)
                .queryParam(color, colorValue)
                .queryParam(height, heightValue)
                .queryParam(material, materialValue)
                .queryParam(sound, soundValue)
                .queryParam(wingsState, wingsStateValue));
    }
    //запрос для плавания, полета и просмотра свойств уточки
    protected void sendGetRequest(TestCaseRunner runner, HttpClient URL, String path, String queName, String queValue) {
        runner.$(http()
                .client(URL)
                .send()
                .get(path)
                .queryParam(queName, queValue));
    }

    //запрос для кряканья уточки
    protected void sendGetRequestQuack(TestCaseRunner runner, HttpClient URL, String path, String queName, String queValue,
                                       int repetitionCount,int repetitionCountValue, int soundCount, int soundCountValue) {
        runner.$(http()
                .client(URL)
                .send()
                .get(path)
                .queryParam(queName, queValue)
                .queryParam (repetitionCount, repetitionCountValue)
                .queryParam (soundCount,soundCountValue)
        );
    }

    public void validateResponse(TestCaseRunner runner, String responseMessage) {
        runner.$(http().client(DuckService)
                .receive()
                .response(HttpStatus.OK)
                .message()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new ClassPathResource(responseMessage)));
    }
}
