package api.test;

import api.payload.User;
import api.routes.UserEndPoints;
import com.github.javafaker.Faker;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static api.routes.UserEndPoints.createUser;

public class UserTests {

    Faker faker;
    User userPayload;

    @BeforeClass
    public void userPayload() {
        RestAssuredConfig.newConfig().sslConfig((new SSLConfig()).relaxedHTTPSValidation().allowAllHostnames());
        faker = new Faker();
        userPayload = new User();
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());
    }

    @Test(priority = 1)
    public void createUserTest() {
        Response response = UserEndPoints.createUser(userPayload);
        ResponseBody responseBody = response.getBody();
        System.out.println(responseBody.prettyPrint());
    }

    @Test(priority = 2)
    public void getUserTest() {
        Response response = UserEndPoints.getUser(userPayload.getUsername());
        ResponseBody responseBody = response.getBody();
        System.out.println(responseBody.prettyPrint());
    }

    @Test(priority = 3)
    public void updateUserTest() {
        Response response = UserEndPoints.updateUser(userPayload.getUsername(), userPayload.getFirstName(), userPayload.getLastName());
        ResponseBody responseBody = response.getBody();
        System.out.println(responseBody.prettyPrint());
    }

    @Test(priority = 4)
    public void deleteUserTest() {
        Response response = UserEndPoints.deleteUser(userPayload.getUsername());
        ResponseBody responseBody = response.getBody();
        System.out.println(responseBody.prettyPrint());
    }
}
