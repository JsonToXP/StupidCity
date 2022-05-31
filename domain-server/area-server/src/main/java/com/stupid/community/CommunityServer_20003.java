package com.stupid.community;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@Slf4j
public class CommunityServer_20003 {

    public static void main(String[] args) {
        ConfigurableEnvironment env = SpringApplication.run(CommunityServer_20003.class, args).getEnvironment();
        log.info("\n" +
                "  _________                                     .__  __             _________                                \n" +
                "\\_   ___ \\  ____   _____   _____  __ __  ____ |__|/  |_ ___.__.  /   _____/ ______________  __ ___________ \n" +
                "/    \\  \\/ /  _ \\ /     \\ /     \\|  |  \\/    \\|  \\   __<   |  |  \\_____  \\_/ __ \\_  __ \\  \\/ // __ \\_  __ \\\n" +
                "\\     \\___(  <_> )  Y Y  \\  Y Y  \\  |  /   |  \\  ||  |  \\___  |  /        \\  ___/|  | \\/\\   /\\  ___/|  | \\/\n" +
                " \\______  /\\____/|__|_|  /__|_|  /____/|___|  /__||__|  / ____| /_______  /\\___  >__|    \\_/  \\___  >__|   \n" +
                "        \\/             \\/      \\/           \\/          \\/              \\/     \\/                 \\/    \n" +
                "                                                                  Community-Server Port : {}",env.getProperty("server.port"));
    }
}
