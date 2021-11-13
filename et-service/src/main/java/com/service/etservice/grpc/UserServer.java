package com.service.etservice.grpc;


import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author 13465
 */
@Component
public class UserServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServer.class);

    private Server server;

    public void start() throws IOException {
        int port = 52095;
        server = ServerBuilder.forPort(port).addService(new UserServiceIml())
                .build()
                .start();
        LOGGER.info("服务启动成功 渡口为:{}", port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LOGGER.error("关闭 grpc jvm 已关闭");

        }));
    }

    public void stop() throws InterruptedException {
        if (null != server) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    private static class UserServiceIml extends UserServiceGrpc.UserServiceImplBase {
        @Override
        public void getUserByLoginName(loginName request, StreamObserver<User> responseObserver) {
            String loginName = request.getLoginName();
            if ("xiaofei".equals(loginName)) {
                responseObserver.onNext(User.newBuilder().setName("xiafei")
                        .setEmail("1346574090@qq.com")
                        .setPassword("123456").setLoginName("xiaofei").build());
            }
            responseObserver.onCompleted();
        }
    }
}
