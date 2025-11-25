package com.thiagobarbosa.api.utils;

import com.github.javafaker.Faker;
import com.thiagobarbosa.api.models.User;


public class FakerUtil {

    private static final Faker faker = new Faker();

    public static User generateUsers() {

        return User.builder()
                .name(faker.name().fullName())
                .email(faker.internet().emailAddress())
                .gender(faker.options().option("male", "female"))
                .status("active")
                .build();

    }
}
