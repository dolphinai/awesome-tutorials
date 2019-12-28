package com.github.dolphinai.tutorials.eurekaserver;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {

    public static void main(String[] args) {

        // java -jar target/eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer1
        // java -jar target/eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer2
        new SpringApplicationBuilder(EurekaServerApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }

}
