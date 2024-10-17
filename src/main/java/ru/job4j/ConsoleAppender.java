package ru.job4j;

public class ConsoleAppender implements Appender {

    private LogLevel logLevel;

    public ConsoleAppender(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    @Override
    public void append(String message) {
        System.out.println(message);
    }

    @Override
    public LogLevel getLogLevel() {
        return logLevel;
    }
}
