package org.example.app.springdataemployees;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringDataEmployeesApplication {
    private static final Logger APP_LOGGER = LogManager.getLogger(SpringDataEmployeesApplication.class);
    private static final Logger CONSOLE_LOGGER = LogManager.getLogger("console_logger");

    public static void main(String[] args) {

       new SpringApplication(SpringDataEmployeesApplication.class).run(args);
        APP_LOGGER.info("APP is running...");
        CONSOLE_LOGGER.info("APP is running...");
    }

}
