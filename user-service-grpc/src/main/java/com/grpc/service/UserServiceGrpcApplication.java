package com.grpc.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

/**
 * @author 13465
 */
@SpringBootApplication
public class UserServiceGrpcApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(UserServiceGrpcApplication.class, args);
        UserServer server = context.getBean(UserServer.class);
        try {
            server.start();
            server.blockUntilShutdown();
        } catch (InterruptedException | IOException e) {
            System.out.printf("错误 %s", e.getMessage());
        }

    }

}
