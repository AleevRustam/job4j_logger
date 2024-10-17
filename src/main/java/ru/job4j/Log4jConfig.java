package ru.job4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Log4jConfig {
    private Properties properties = new Properties();

    public Log4jConfig(String configFilePath) {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(configFilePath)) {
            if (input == null) {
                System.out.println("Извините, не удалось найти файл конфигурации: " + configFilePath);
                return;
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
