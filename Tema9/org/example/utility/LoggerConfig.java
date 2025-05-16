package org.example.utility;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerConfig {

    private static final Logger logger = Logger.getLogger("JPQLLogger");

    static {
        try {

            FileHandler fileHandler = new FileHandler("jpql_logs.log", true);
            fileHandler.setFormatter(new SimpleFormatter());

            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(true);

        } catch (IOException e) {
            System.err.println("Failed to initialize logger: " + e.getMessage());
        }
    }

    public static Logger getLogger() {
        return logger;
    }
}