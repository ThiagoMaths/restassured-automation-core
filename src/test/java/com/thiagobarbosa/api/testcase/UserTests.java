package com.thiagobarbosa.api.testcase;

import com.thiagobarbosa.api.models.User;
import com.thiagobarbosa.api.services.UserApiClient;
import com.thiagobarbosa.api.utils.FakerUtil;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;

public class UserTests {

    private UserApiClient userClient;
    private User testUser;
    private int userId;
    private Response response;


    @BeforeClass
    public void init() {
        userClient = new UserApiClient();
    }

    @BeforeMethod
    public void setup() {
        testUser = FakerUtil.generateUsers();
        Response response = userClient.createUser(testUser);
        userId = response.jsonPath().getInt("id");
    }

    @Test
    public void shouldReadUserSuccessfully() {
         response =  userClient.getUser(userId);

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("email"), testUser.getEmail());

    }

    @Test
    public void shouldUpdateUserSuccessfully() {
        User updateUser = FakerUtil.generateUsers();

        response = userClient.updateUser(userId, updateUser);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("name"), updateUser.getName());
    }

    @Test
    public void shouldDeleteUserSuccessfully() {

        userClient.deleteUser(userId)
                .then()
                .statusCode(204);

        userClient.getUser(userId)
                .then()
                .statusCode(404);

        userId = 0;

    }

    @AfterMethod
    public void teardown() {
        if (userId > 0){
            userClient.deleteUser(userId);
        }
    }


}