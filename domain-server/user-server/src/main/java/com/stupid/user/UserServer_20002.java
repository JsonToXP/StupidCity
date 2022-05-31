package com.stupid.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@Slf4j
public class UserServer_20002 {

    public static void main(String[] args) {
        ConfigurableEnvironment env = SpringApplication.run(UserServer_20002.class, args).getEnvironment();
        log.info("\n" +
                " ____ ___                       _________                                \n" +
                "|    |   \\______ ___________   /   _____/ ______________  __ ___________ \n" +
                "|    |   /  ___// __ \\_  __ \\  \\_____  \\_/ __ \\_  __ \\  \\/ // __ \\_  __ \\\n" +
                "|    |  /\\___ \\\\  ___/|  | \\/  /        \\  ___/|  | \\/\\   /\\  ___/|  | \\/\n" +
                "|______//____  >\\___  >__|    /_______  /\\___  >__|    \\_/  \\___  >__|   \n" +
                "             \\/     \\/                \\/     \\/                 \\/      \n" +
                "                                                Dev-Server Port : {}",env.getProperty("server.port"));
    }
}
