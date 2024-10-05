package api.routes;

import api.payload.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserEndPoints {
    public static Response createUser(User payload) {
        Response response = given()
                                    .relaxedHTTPSValidation()
                                    .contentType(ContentType.JSON)
                                    .accept(ContentType.JSON)
                                    .body(payload)
                                    .when()
                                    .post(Routes.createUserUrl);
        return response;
    }

    public static Response getUser(String username) {
        Response response = given().relaxedHTTPSValidation()
                                    .pathParam("username", username)
                                    .contentType(ContentType.JSON)
                                    .accept(ContentType.JSON)
                                    .when()
                                    .get(Routes.getUserUrl);
        return response;
    }

    public static Response updateUser(String username, String fname, String lname) {
        Map<String, String> obj = new HashMap<String, String>();
        obj.put("firstName", fname);
        obj.put("lastName", lname);

        Response response = given().relaxedHTTPSValidation()
                                    .pathParam("username", username)
                                    .contentType(ContentType.JSON)
                                    .accept(ContentType.JSON)
                                    .body(obj)
                                    .when()
                                    .post(Routes.updateUserUrl);
        return response;
    }

    public static Response deleteUser(String username) {
        Response response = given().relaxedHTTPSValidation()
                                    .pathParam("username", username)
                                    .contentType(ContentType.JSON)
                                    .accept(ContentType.JSON)
                                    .when()
                                    .delete(Routes.deleteUserUrl);
        return response;
    }
}
