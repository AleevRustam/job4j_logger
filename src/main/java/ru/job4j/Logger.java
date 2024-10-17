package ru.job4j;

import java.util.ArrayList;
import java.util.List;

public class Logger {

    private String name;
    private LogLevel level;
    private List<Appender> appenders = new ArrayList<>();

    public Logger(String name, LogLevel level, List<Appender> appenders) {
        this.name = name;
        this.level = level;
        this.appenders = appenders;
    }

    public static Logger fromConfig(Log4jConfig config) {
        String loggerName = config.getProperty("log4j.rootLogger").split(",")[0].trim();
        LogLevel loggerLevel = LogLevel.valueOf(loggerName);
        List<Appender> appenders = new ArrayList<>();

        if (config.getProperty("log4j.appender.CONSOLE_LOG") != null) {
            LogLevel consoleLevel = LogLevel.valueOf(config.getProperty("log4j.appender.CONSOLE_LOG.Threshold"));
            appenders.add(new ConsoleAppender(consoleLevel));
        }

        if (config.getProperty("log4j.appender.FILE_LOG") != null) {
            String filePath = config.getProperty("log4j.appender.FILE_LOG.file");
            LogLevel fileLevel = LogLevel.valueOf(config.getProperty("log4j.appender.FILE_LOG.Threshold"));
            appenders.add(new FileAppender(filePath, fileLevel));
        }

        return new Logger(loggerName, loggerLevel, appenders);
    }

    public void log(LogLevel level, String message) {
        if (level.getPriority() >= this.level.getPriority()) {
            String formattedMessage = String.format("[%s] [%s]: %s", level, name, message);

            for (Appender appender : appenders) {
                if (level.getPriority() >= appender.getLogLevel().getPriority()) {
                    appender.append(formattedMessage);
                }
            }
        }
    }

    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void warn(String message) {
        log(LogLevel.WARN, message);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }
}
