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
        IFirstSevice firstSevice = rpcClientProxy.clientProxy(IFirstSevice.class,"");

        System.out.println(firstSevice.first("我"));
    }

}
