package ru.job4j;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoggerTest {
    private Logger logger;
    ByteArrayOutputStream outContent;
    PrintStream originalOut;

    @BeforeEach
    public void setUp() {
        List<Appender> appenders = new ArrayList<>();
        appenders.add(new ConsoleAppender()); // Добавляем консольный аппендер
        appenders.add(new FileAppender("logs/debug.txt"));
        logger = new Logger("TestLogger", LogLevel.DEBUG, appenders);
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testDebugLogging() {
        logger.debug("This is a debug message");
        String output = outContent.toString();
        assertTrue(output.contains("DEBUG"));
        assertTrue(output.contains("This is a debug message"));
        System.setOut(originalOut);
    }

    @Test
    public void testInfoLogging() {
        logger.info("This is an info message");
        String output = outContent.toString();
        assertTrue(output.contains("INFO"));
        assertTrue(output.contains("This is an info message"));
        System.setOut(originalOut);
    }

    @Test
    public void testWarnLogging() {
        logger.warn("This is a warn message");
        String output = outContent.toString();
        assertTrue(output.contains("WARN"));
        assertTrue(output.contains("This is a warn message"));
        System.setOut(originalOut);
    }

    @Test
    public void testErrorLogging() {
        logger.error("This is an error message");
        String output = outContent.toString();
        assertTrue(output.contains("ERROR"));
        assertTrue(output.contains("This is an error message"));
        System.setOut(originalOut);
    }
}