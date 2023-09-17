import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class GetJsonHomeworkTest {
    @Test
    public void GetJsonHomework()
    {
        JsonPath response = RestAssured
                .given()
                .get("https://playground.learnqa.ru/api/get_json_homework")
                .jsonPath();

        Map<String, String> message = new HashMap<>(response.get("messages[1]"));
        System.out.println(message);
    }
}
