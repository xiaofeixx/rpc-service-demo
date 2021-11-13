package com.rpc.user.userservice.server;

import com.rpc.user.userservice.rest.UserRestServiceIml;
import com.rpc.user.userservice.service.UserService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 13465
 */
@Component
public class UserServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServer.class);

    @Value("${thrift.port}")
    private int port;
    @Value("${thrift.maxThreads}")
    private int maxThreads;
    @Value("${thrift.minThreads}")
    private int minThreads;

    private TBinaryProtocol.Factory tbFactory;

    private TTransportFactory tTransportFactory;

    public void init() {
        tbFactory = new TBinaryProtocol.Factory();
        tTransportFactory = new TTransportFactory();
    }

    @Autowired
    private UserRestServiceIml userRestServiceIml;

    public void start() {
        init();
        UserService.Processor<UserService.Iface> processor
                = new UserService.Processor<>(userRestServiceIml);
        TServerTransport transport = null;
        try {
        transport = new TServerSocket(port);
        TThreadPoolServer.Args args = new TThreadPoolServer.Args(transport);
        args.processor(processor);
        args.protocolFactory(tbFactory);
        args.transportFactory(tTransportFactory);
        args.maxWorkerThreads(maxThreads);
        args.minWorkerThreads(minThreads);
        TServer server = new TThreadPoolServer(args);
        LOGGER.info("服务启动成功,端口为: {}", port);
        server.serve();
        } catch (TTransportException e) {
            LOGGER.error("服务启动失败！{}", e.getMessage());
        }
    }

}
