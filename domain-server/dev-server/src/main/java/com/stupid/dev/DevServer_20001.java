package com.stupid.dev;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@Slf4j
public class DevServer_20001 {

    public static void main(String[] args) {
        ConfigurableEnvironment env = SpringApplication.run(DevServer_20001.class, args).getEnvironment();
        log.info("\n" +
                "    .___                                                    \n" +
                "  __| _/_______  __   ______ ______________  __ ___________ \n" +
                " / __ |/ __ \\  \\/ /  /  ___// __ \\_  __ \\  \\/ // __ \\_  __ \\\n" +
                "/ /_/ \\  ___/\\   /   \\___ \\\\  ___/|  | \\/\\   /\\  ___/|  | \\/\n" +
                "\\____ |\\___  >\\_/   /____  >\\___  >__|    \\_/  \\___  >__|   \n" +
                "     \\/    \\/            \\/     \\/                 \\/       \n" +
                "                                                Dev-Server Port : {}",env.getProperty("server.port"));
    }
}
