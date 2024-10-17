package api.routes;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserEndPoints1 {
   public static ResourceBundle getURL(){
        ResourceBundle routes = ResourceBundle.getBundle("routes");
        return routes;
    }
    public static Response createUser(User payload) {
        String postUrl=getURL().getString("createUserUrl");
        Response response = given()
                                    .relaxedHTTPSValidation()
                                    .contentType(ContentType.JSON)
                                    .accept(ContentType.JSON)
                                    .body(payload)
                                    .when()
                                    .post(postUrl);
        return response;
    }

    public static Response getUser(String username) {
        String getUrl=getURL().getString("getUserUrl");
        Response response = given().relaxedHTTPSValidation()
                                    .pathParam("username", username)
                                    .contentType(ContentType.JSON)
                                    .accept(ContentType.JSON)
                                    .when()
                                    .get(getUrl);
        return response;
    }

    public static Response updateUser(String username, User payload) {
               String putUrl=getURL().getString("updateUserUrl");
               Response response = given().relaxedHTTPSValidation()
                                    .pathParam("username", username)
                                    .contentType(ContentType.JSON)
                                    .accept(ContentType.JSON)
                                    .body(payload)
                                    .when()
                                    .put(putUrl);
        return response;
    }

    public static Response deleteUser(String username) {
       String deleteUrl=getURL().getString("deleteUserUrl");
        Response response = given().relaxedHTTPSValidation()
                                    .pathParam("username", username)
                                    .contentType(ContentType.JSON)
                                    .accept(ContentType.JSON)
                                    .when()
                                    .delete(deleteUrl);
        return response;
    }
}
