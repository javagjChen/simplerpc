package com.cgj.rpc.server;

import com.cgj.rpc.server.zk.IRegistryCenter;
import com.cgj.rpc.server.zk.RegistryCenterImpl;

import java.io.IOException;

/**
 * @Classname LBServerDemo
 * @Description TODO
 * @Date 2019/3/26 9:49
 * @Created by cgj
 */
public class LBServerDemo {
    public static void main(String[] args) throws IOException {
        IRegistryCenter registryCenter = new RegistryCenterImpl();

        IFirstSevice firstSevice = new FirstSerivceImpl();
        RPCServer rpcServer = new RPCServer(registryCenter,"127.0.0.1:8080");
        rpcServer.binding(firstSevice);
        rpcServer.publisher();

        System.in.read();
    }
}
