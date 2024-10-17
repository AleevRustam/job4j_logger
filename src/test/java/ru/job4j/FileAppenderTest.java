package ru.job4j;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

import static org.junit.jupiter.api.Assertions.*;

class FileAppenderTest {
    private static final String TEST_FILE_PATH = "logs/test_log.txt";
    private FileAppender fileAppender;

    @BeforeEach
    public void setUp() {
        File testFile = new File(TEST_FILE_PATH);
        if (testFile.exists()) {
            testFile.delete();
        }

        fileAppender = new FileAppender(TEST_FILE_PATH, LogLevel.DEBUG);
    }

    @AfterEach
    public void tearDown() {
        File testFile = new File(TEST_FILE_PATH);
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    public void testAppendMessage() {
        String message = "This is a test message";
        fileAppender.append(message);

        String readMessage = readLastLineFromFile(TEST_FILE_PATH);
        assertEquals(message, readMessage);
    }

    @Test
    public void testAppendMultipleMessages() {
        String message1 = "First message";
        String message2 = "Second message";
        fileAppender.append(message1);
        fileAppender.append(message2);

        String readMessage = readLastLineFromFile(TEST_FILE_PATH);
        assertEquals(message2, readMessage);
    }

    private String readLastLineFromFile(String filePath) {
        String lastLine = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                lastLine = currentLine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastLine;
    }
}