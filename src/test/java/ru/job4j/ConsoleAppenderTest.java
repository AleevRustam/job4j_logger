package ru.job4j;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleAppenderTest {
    private ConsoleAppender consoleAppender;

    @BeforeEach
    public void setUp() {
        consoleAppender = new ConsoleAppender();
    }

    @Test
    public void testAppend() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        String testMessage = "This is a test message";
        consoleAppender.append(testMessage);

        assertEquals(testMessage + System.lineSeparator(), outContent.toString());

        System.setOut(originalOut);
    }
}