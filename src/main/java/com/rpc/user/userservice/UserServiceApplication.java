package com.rpc.user.userservice;

import com.rpc.user.userservice.server.UserServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author 13465
 */
@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext
                = SpringApplication.run(UserServiceApplication.class, args);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String port = environment.getProperty("server.port");
        String address = environment.getProperty("server.address");
        System.out.println(address);
        System.out.println(port);
        UserServer userServer = applicationContext.getBean(UserServer.class);
        userServer.start();
    }

}
