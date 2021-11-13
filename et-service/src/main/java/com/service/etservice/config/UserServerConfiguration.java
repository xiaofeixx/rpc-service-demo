package com.service.etservice.config;

import com.service.etservice.rest.UserRpcClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 13465
 */
@Configuration
@ConfigurationProperties(prefix = "user.service")
public class UserServerConfiguration {

    private String host;

    private int port;

    @Bean(initMethod = "init")
    public UserRpcClient userRpcClient() {
        UserRpcClient client = new UserRpcClient();
        client.setHost(host);
        client.setPort(port);
        return client;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
