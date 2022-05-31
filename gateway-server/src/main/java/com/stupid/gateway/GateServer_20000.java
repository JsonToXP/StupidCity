package com.stupid.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class GateServer_20000 {

    public static void main(String[] args) {
        ConfigurableEnvironment env = SpringApplication.run(GateServer_20000.class, args).getEnvironment();
        log.info("\n" +
                "  ________        __                                  _________                                \n" +
                " /  _____/_____ _/  |_  ______  _  _______  ___.__.  /   _____/ ______________  __ ___________ \n" +
                "/   \\  ___\\__  \\\\   __\\/ __ \\ \\/ \\/ /\\__  \\<   |  |  \\_____  \\_/ __ \\_  __ \\  \\/ // __ \\_  __ \\\n" +
                "\\    \\_\\  \\/ __ \\|  | \\  ___/\\     /  / __ \\\\___  |  /        \\  ___/|  | \\/\\   /\\  ___/|  | \\/\n" +
                " \\______  (____  /__|  \\___  >\\/\\_/  (____  / ____| /_______  /\\___  >__|    \\_/  \\___  >__|   \n" +
                "        \\/     \\/          \\/             \\/\\/              \\/     \\/                 \\/      \n" +
                "                                                                Gateway-Server Port : {}",env.getProperty("server.port"));
    }
}
