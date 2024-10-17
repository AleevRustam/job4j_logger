package ru.job4j;

public interface Appender {
    void append(String message);
    LogLevel getLogLevel();
}
