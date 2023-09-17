import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

public class LongtimeJobTest {
    @Test
    public void LongtimeJob() throws InterruptedException {
        // 1) создавал задачу
        JsonPath response1 = RestAssured
                .given()
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .jsonPath();

        response1.prettyPrint();
        String token = response1.get("token");
        int seconds = response1.get("seconds");

        // 2) делал один запрос с token ДО того, как задача готова, убеждался в правильности поля status
        JsonPath response2 = RestAssured
                .given()
                .queryParam("token", token)
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .jsonPath();

        response2.prettyPrint();
        assert response2.get("status").equals("Job is NOT ready"): "Status not equal to \"Job is NOT ready\"";

        // 3) ждал нужное количество секунд с помощью функции Thread.sleep()
        Thread.sleep(seconds*1000L);

        // 4) делал один запрос с token ДО того, как задача готова, убеждался в правильности поля status
        JsonPath response3 = RestAssured
                .given()
                .queryParam("token", token)
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .jsonPath();

        response3.prettyPrint();
        assert response3.get("status").equals("Job is ready"): "Status not equal to \"Job is ready\"";
        assert response3.get("result") != null: "result is NULL";
    }
}
