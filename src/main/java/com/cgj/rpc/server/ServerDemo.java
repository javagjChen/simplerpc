package com.cgj.rpc.server;

import com.cgj.rpc.server.zk.IRegistryCenter;
import com.cgj.rpc.server.zk.RegistryCenterImpl;

import java.io.IOException;

/**
 * @Classname ServerDemo
 * @Description TODO
 * @Date 2019/3/22 17:50
 * @Created by cgj
 */
public class ServerDemo {
    public static void main(String[] args) throws IOException {
        IRegistryCenter registryCenter = new RegistryCenterImpl();

        IFirstService firstService = new FirstServiceImpl();
        RPCServer rpcServer = new RPCServer(registryCenter,"127.0.0.1:8080");
        rpcServer.binding(firstService);
        rpcServer.publisher();


        System.in.read();
    }
}
