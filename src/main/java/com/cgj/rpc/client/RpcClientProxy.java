package com.cgj.rpc.client;

import com.cgj.rpc.client.zk.IDiscovery;

import java.lang.reflect.Proxy;

/**
 * @Classname RpcClientProxy
 * @Description TODO
 * @Date 2019/3/24 17:25
 * @Created by cgj
 */
public class RpcClientProxy {

    private IDiscovery discovery;

    public RpcClientProxy(IDiscovery discovery) {
        this.discovery = discovery;
    }

    public <T> T clientProxy(Class<T> interfaceCl,String version){
        return (T) Proxy.newProxyInstance(interfaceCl.getClassLoader(),new Class[] {interfaceCl},new RemoteInvocationHandler(discovery,version));
    }
}
