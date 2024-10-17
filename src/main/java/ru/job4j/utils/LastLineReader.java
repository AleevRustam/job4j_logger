package ru.job4j.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LastLineReader {

    public static String readLastLineFromFile(String filePath) {

        String lastLine = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lastLine = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastLine;
    }
}
