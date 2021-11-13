package com.service.etservice.rest;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * 远程调用客户端
 * @author xiaofeixx
 */
public class UserRpcClient {

    private String host;

    private int port;

    private UserService.Client client;

    private TBinaryProtocol protocol;

    private TSocket socket;

    public void init() throws TTransportException {
        socket = new TSocket(host, port);
        protocol = new TBinaryProtocol(socket);
        client = new UserService.Client(protocol);
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

    public UserService.Client getClient() {
        return client;
    }

    public void setClient(UserService.Client client) {
        this.client = client;
    }

    public void open() throws TTransportException {
        socket.open();
    }

    public void close() {
        socket.close();
    }
}
