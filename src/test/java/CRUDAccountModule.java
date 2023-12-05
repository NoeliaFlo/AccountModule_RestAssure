import org.json.JSONObject;
import org.testng.annotations.Test;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CRUDAccountModule {

    @Test(priority = 0)
    void createAccount(){
        String postUrl = Helpers.getAccountUrl();

        JSONObject requestBody = new JSONObject()
                .put("userName", Helpers.username)
                .put("password", Helpers.password);

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

        Helpers.userId = response.jsonPath().getString("userID");
    }

    @Test (priority = 1)
    void getToken(){
        String tokenUrl = Helpers.getTokenUrl();

        JSONObject requestBody = new JSONObject()
                .put("userName", Helpers.username)
                .put("password", Helpers.password);

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

        Helpers.token  = response.jsonPath().getString("token");
        System.out.println(Helpers.token);
    }

    @Test (priority = 2)
    void authorizedSuccess(){
        String authorizedUrl = Helpers.getAuthorizedUrl();

        JSONObject requestBody = new JSONObject()
                .put("userName", Helpers.username)
                .put("password", Helpers.password);

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
        String userByUserIDUrl = Helpers.getUserByUserIDUrl()+ Helpers.userId;

        given()
            .header("Accept", "application/json")
            .header("Authorization", "Bearer " + Helpers.token)
        .when()
                .get(userByUserIDUrl)
        .then()
            .statusCode(200)
            .log().all();
    }

    @Test(priority = 4)
    void deleteUserByID(){
        String deleteUserByUserIDUrl = Helpers.getUserByUserIDUrl()+ Helpers.userId;

        given()
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + Helpers.token)
        .when()
                .delete(deleteUserByUserIDUrl)
        .then()
                .statusCode(200)
                .log().all();
    }

}


