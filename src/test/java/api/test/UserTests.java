package api.test;

import api.payload.User;
import api.routes.UserEndPoints;
import com.github.javafaker.Faker;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests {

    Faker faker;
    User userPayload;
    public Logger logger;
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
       logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void createUserTest() {
        logger.info("Create User Method is Called");
        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 2)
    public void getUserTest() {
        logger.info("Get User Method is Called");
        Response response = UserEndPoints.getUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 3)
    public void updateUserTest() {
        logger.info("Put User Method is Called");
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        Response response = UserEndPoints.updateUser(this.userPayload.getUsername(),userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

        //Checking Data After Update
        Response responseAfterUpdate = UserEndPoints.getUser(this.userPayload.getUsername());
        responseAfterUpdate.then().log().all();

    }

    @Test(priority = 4)
    public void deleteUserTest() {
        logger.info("Delete User Method is Called");
        Response response = UserEndPoints.deleteUser(userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);;
    }
}
