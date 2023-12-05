import org.json.JSONObject;
import org.testng.annotations.Test;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CRUDAccountModuleTest {

    @Test(priority = 0)
    void createAccount(){
        String postUrl = Helper.getAccountUrl();

        JSONObject requestBody = new JSONObject()
                .put("userName", Helper.username)
                .put("password", Helper.password);

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

        Helper.userId = response.jsonPath().getString("userID");
    }

    @Test (priority = 1)
    void getToken(){
        String tokenUrl = Helper.getTokenUrl();

        JSONObject requestBody = new JSONObject()
                .put("userName", Helper.username)
                .put("password", Helper.password);

        Response response = given()
                                .header("Accept", "application/json")
                                .header("Content-Type", "application/json")
                                .body(requestBody.toString())
                            .when()
                                .post(tokenUrl)
                            .then()
                                .statusCode(200)
                                .log().all()
                                .extract()
                                .response();

        Helper.token  = response.jsonPath().getString("token");
        System.out.println(Helper.token);
    }

    @Test (priority = 2)
    void authorizedSuccess(){
        String authorizedUrl = Helper.getAuthorizedUrl();

        JSONObject requestBody = new JSONObject()
                .put("userName", Helper.username)
                .put("password", Helper.password);

        given()
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
            .body(requestBody.toString())
        .when()
            .post(authorizedUrl)
        .then()
            .statusCode(200).body(equalTo("true"))
            .log().all();
    }

    @Test(priority = 3)
    void getUserSuccess(){
        String userByUserIDUrl = Helper.getUserByUserIDUrl()+ Helper.userId;

        given()
            .header("Accept", "application/json")
            .header("Authorization", "Bearer " + Helper.token)
        .when()
                .get(userByUserIDUrl)
        .then()
            .statusCode(200)
            .log().all();
    }

    @Test(priority = 4)
    void deleteUserByID(){
        String deleteUserByUserIDUrl = Helper.getUserByUserIDUrl()+ Helper.userId;

        given()
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + Helper.token)
        .when()
                .delete(deleteUserByUserIDUrl)
        .then()
                .statusCode(200)
                .log().all();
    }
}


