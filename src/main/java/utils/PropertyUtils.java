package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {
    private static Properties properties;

    public static String get(String key) throws IOException {
        if (properties == null) {
            properties = new Properties();
            FileInputStream input = new FileInputStream("F:\\Java\\Projects\\Reqres_RestAssured\\src\\test\\resources\\userTestData.properties");
            properties.load(input);
            input.close();
        }
        return properties.getProperty(key);
    }
}
