import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CRUDAccountModule {
    @Test(priority = 1)
    void createUser(){
        JSONObject user = new JSONObject();
        user.put("userName", Helper.DEFAULT_USERNAME);
        user.put("password", Helper.DEFAULT_PASSWORD);

        Helper.userId = given()
                .contentType("application/json")
                .body(user)
        .when()
                .post(Helper.URL)
        .then()
                .statusCode(201)
                .body("userName", equalTo(Helper.DEFAULT_USERNAME))
                .body("password", equalTo(Helper.DEFAULT_PASSWORD))
                .log().all()
                .extract().jsonPath().getString("userId");

        System.out.println("userId: " + Helper.userId);
    }
}
