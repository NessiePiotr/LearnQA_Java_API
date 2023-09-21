import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomeworkHeaderTest {

    @Test
    public void HomeworkHeader(){
        Response response = RestAssured
                .given()
                .get("https://playground.learnqa.ru/api/homework_header")
                .andReturn();

        Headers headers = response.getHeaders();
        assertTrue(headers.hasHeaderWithName("x-secret-homework-header"),
                "Header 'x-secret-homework-header' does not exists");
        assertEquals(headers.get("x-secret-homework-header").getValue(), "Some secret value", "'x-secret-homework-header' has invalid value");
    }
}
