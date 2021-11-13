package com.service.etservice.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author 13465
 */
public class UserClient {


    private final ManagedChannel channel;

    private final UserServiceGrpc.UserServiceBlockingStub userServiceStub;

    public UserClient (String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        userServiceStub = UserServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(30, TimeUnit.SECONDS);
    }

    public User getUserByLoginName(loginName loginName) {
        return userServiceStub.getUserByLoginName(loginName);
    }

    public static void main(String[] args) throws InterruptedException {
        UserClient client = new UserClient("192.168.0.25", 52095);
        loginName loginNameParam = loginName.newBuilder().setLoginName("xiaofei").build();
        User loginName = client.getUserByLoginName(loginNameParam);
        System.out.println(loginName);
        client.shutdown();
    }


}
