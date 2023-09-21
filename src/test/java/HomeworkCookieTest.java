import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomeworkCookieTest {

    @Test
    public void HomeworkCookie(){
        Response response = RestAssured
                .given()
                .get("https://playground.learnqa.ru/api/homework_cookie")
                .andReturn();

        HashMap<String, String> cookies = new HashMap<>(response.getCookies());
        assertTrue(cookies.containsKey("HomeWork"), "'HomeWork' cookie is not returned");
        assertEquals(cookies.get("HomeWork"), "hw_value", "'HomeWork' cookie has invalid value");
    }

}
