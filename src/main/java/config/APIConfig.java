package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class APIConfig {
    private static Properties properties;
    public static void readFromAPIProperty() throws IOException {
        properties=new Properties();
        FileInputStream input=new FileInputStream("F:\\Java\\Projects\\Reqres_RestAssured\\src\\main\\resources\\config.properties");
        properties.load(input);
    }
    public static String getBaseURL() throws IOException {
        readFromAPIProperty();
        String url= properties.getProperty("base.url");
        return url;
    }
    public static int getMaxRetryCount(){
        return Integer.parseInt(properties.getProperty("max.retry.count"));
    }
    public static int getTimeout(){
        return Integer.parseInt(properties.getProperty("timeout"));
    }
}
