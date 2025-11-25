package com.thiagobarbosa.api.base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class SpecsBuilder {

    public static RequestSpecification authenticate() {

        return new RequestSpecBuilder()
                .setBaseUri(ConfigManager.getBaseUrl())
                .addHeader("Authorization", ConfigManager.getToken())
                .setContentType("application/json")
                .setAccept("application/json")
                .build();

    }
}
