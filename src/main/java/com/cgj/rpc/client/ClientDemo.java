package com.cgj.rpc.client;

import com.cgj.rpc.client.zk.DiscoveryImpl;
import com.cgj.rpc.client.zk.IDiscovery;

/**
 * @Classname ClientDemo
 * @Description TODO
 * @Date 2019/3/23 18:45
 * @Created by cgj
 */
public class ClientDemo {

    public static void main(String[] args) {
        IDiscovery discovery = new DiscoveryImpl();
        RpcClientProxy rpcClientProxy = new RpcClientProxy(discovery);
        IFirstService firstService = rpcClientProxy.clientProxy(IFirstService.class,"");

        for (int i = 0;i <10;i++) {
            System.out.println(firstService.first("我"));
        }
    }

}
