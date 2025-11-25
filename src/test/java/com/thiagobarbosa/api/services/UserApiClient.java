package com.thiagobarbosa.api.services;

import com.thiagobarbosa.api.base.SpecsBuilder;
import com.thiagobarbosa.api.models.User;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class UserApiClient {

    private static final String USER_ENDPOINT = "/users";

    public Response createUser(User user) {
        return given()
                .spec(SpecsBuilder.authenticate())
                .body(user)
                .when()
                .post(USER_ENDPOINT);
    }

    public Response getUser(int id) {
        return given()
                .spec(SpecsBuilder.authenticate())
                .when()
                .get(USER_ENDPOINT + "/" + id);
    }

    public Response updateUser(int id, User user) {
        return given()
                .spec(SpecsBuilder.authenticate())
                .body(user)
                .when()
                .put(USER_ENDPOINT + "/" + id);
    }

    public Response deleteUser(int id) {
        return given()
                .spec(SpecsBuilder.authenticate())
                .when()
                .delete(USER_ENDPOINT + "/" + id);
    }

}
