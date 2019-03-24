package com.cgj.rpc.client;


import com.cgj.rpc.RpcRequest;
import com.cgj.rpc.client.zk.IDiscovery;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 *
 */
public class RemoteInvocationHandler implements InvocationHandler {
    private IDiscovery discovery;

    private String version;

    public RemoteInvocationHandler(IDiscovery discovery, String version) {
        this.discovery=discovery;
        this.version=version;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //组装请求
        RpcRequest request=new RpcRequest();
        request.setClassName(method.getDeclaringClass().getName().substring(method.getDeclaringClass().getName().lastIndexOf(".")+1));
        request.setMethodName(method.getName());
        request.setParameters(args);
        request.setVersion(version);

        String serviceAddress = discovery.discovery(request.getClassName()); //根据接口名称得到对应的服务地址
        //通过tcp传输协议进行传输
        TCPTransport tcpTransport=new TCPTransport(serviceAddress);
        //发送请求
        return tcpTransport.send(request);
    }
}
