package com.cgj.rpc.server;

import com.cgj.rpc.server.zk.IRegistryCenter;
import com.cgj.rpc.server.zk.RegistryCenterImpl;

import java.io.IOException;

/**
 * @Classname LBServerDemo1
 * @Description TODO
 * @Date 2019/3/26 9:49
 * @Created by cgj
 */
public class LBServerDemo1 {
    public static void main(String[] args) throws IOException {

        IRegistryCenter registryCenter = new RegistryCenterImpl();

        IFirstSevice firstSevice = new FirstSerivceImpl2();
        RPCServer rpcServer = new RPCServer(registryCenter,"127.0.0.1:8081");
        rpcServer.binding(firstSevice);
        rpcServer.publisher();

        System.in.read();

    }
}
