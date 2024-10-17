package ru.job4j;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileAppender implements Appender {
    private String filePath;
    private LogLevel logLevel;

    public FileAppender(String filePath, LogLevel logLevel) {
        this.filePath = filePath;
        this.logLevel = logLevel;
    }

    private void createLogDirectory() {
        File logFile = new File(filePath);
        File logDir = logFile.getParentFile();
        if (logDir != null && !logDir.exists()) {
            logDir.mkdirs();
        }
    }

    @Override
    public void append(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
        }
    }

    @Override
    public LogLevel getLogLevel() {
        return logLevel;
    }
}
