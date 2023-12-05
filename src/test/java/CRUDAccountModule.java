import org.json.JSONObject;
import org.testng.annotations.Test;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CRUDAccountModule {

    @Test
    void createAccount(){
        String postUrl = Helpers.getAccountUrl();

        JSONObject requestBody = new JSONObject()
                .put("userName", Helpers.getUsername())
                .put("password", Helpers.getPassword());

        Response response = given()
                                .header("Accept", "application/json")
                                .header("Content-Type", "application/json")
                                .body(requestBody.toString())
                            .when()
                                .post(postUrl)
                            .then()
                                .statusCode(201)
                                .log().all()
                                .extract()
                                .response();

        String userId = response.jsonPath().getString("userID");
        Helpers.setUserID(userId);
        System.out.println(userId);
    }
}
