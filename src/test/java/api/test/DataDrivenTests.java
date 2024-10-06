package api.test;

import api.payload.User;
import api.routes.UserEndPoints;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenTests {
    User userPayload = new User();
    @Test(priority = 1,dataProvider = "Data", dataProviderClass = DataProviders.class, enabled = true)
    public void createUserMethod(String id,String username,String firstname,String lastname,String email,String password,String phone,String userstatus){
        userPayload.setId(Integer.parseInt(id));
        userPayload.setUsername(username);
        userPayload.setFirstName(firstname);
        userPayload.setLastName(lastname);
        userPayload.setEmail(email);
        userPayload.setPassword(password);
        userPayload.setPhone(phone);
        userPayload.setUserStatus(Integer.parseInt(userstatus));
        Response response=UserEndPoints.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(priority = 2,dataProvider = "UserName",dataProviderClass = DataProviders.class)
    public void getUserMethod(String username) {
        Response response = UserEndPoints.getUser(username);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(priority = 3,dataProvider = "UserName",dataProviderClass = DataProviders.class)
    public void deleteUserMethod(String username) {
        Response response = UserEndPoints.deleteUser(username);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);;
    }
}
