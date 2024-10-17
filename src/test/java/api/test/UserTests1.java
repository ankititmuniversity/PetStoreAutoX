package api.test;

import api.payload.User;
import api.routes.UserEndPoints1;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class UserTests1 {

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
    public void createUserTest() throws IOException {
        logger.info("Create User Method is Called");
        Response response = UserEndPoints1.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 2)
    public void getUserTest() throws JsonProcessingException {
        logger.info("Get User Method is Called");
        Response response = UserEndPoints1.getUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

        String jsonObject =     response.asPrettyString();

    }

    @Test(priority = 3)
    public void updateUserTest() throws JsonProcessingException {
        logger.info("Put User Method is Called");
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        Response response = UserEndPoints1.updateUser(this.userPayload.getUsername(),userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

        //Checking Data After Update
        Response responseAfterUpdate = UserEndPoints1.getUser(this.userPayload.getUsername());
        responseAfterUpdate.then().log().all();

        String jsonObject =     response.asPrettyString();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Object> updatedData =objectMapper.readValue(jsonObject, new TypeReference<Map<String, Object>>() {
        });
       Iterator<Map.Entry<String,Object>>entryKey= updatedData.entrySet().iterator();
        while(entryKey.hasNext()){
            Map.Entry<String,Object> entry = entryKey.next();
            System.out.println("Key : "+entry.getKey()+" Value : "+ entry.getValue());
        }

    }

    @Test(priority = 4)
    public void deleteUserTest() {
        logger.info("Delete User Method is Called");
        Response response = UserEndPoints1.deleteUser(userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);;
    }
}
