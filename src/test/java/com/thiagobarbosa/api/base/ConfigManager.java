package com.thiagobarbosa.api.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class ConfigManager {
    private static final Properties properties = new Properties();

    static {
        try(InputStream inputStream = SpecsBuilder.class.getClassLoader().getResourceAsStream("config.properties")){
            if(inputStream == null){
                throw new RuntimeException("config.properties resource not found");
            }
            properties.load(Objects.requireNonNull(inputStream));
        } catch (IOException e){
            throw new RuntimeException("Error",e);
        }
    }

    public static String getBaseUrl(){
        return properties.getProperty("base.url");
    }

    public static String getToken(){
        return properties.getProperty("api.token");
    }
}
