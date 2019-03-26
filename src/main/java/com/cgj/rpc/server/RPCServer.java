package com.cgj.rpc.server;

import com.cgj.rpc.server.zk.IRegistryCenter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Classname RPCServer
 * @Description 用于发布一个远程服务
 * @Date 2019/3/22 18:14
 * @Created by cgj
 */
public class RPCServer {
    //注册中心
    private IRegistryCenter registryCenter;
    //服务地址
    private String serviceAddress;
    // 存放服务名称和服务对象之间的关系
    private Map<String,Object> handlerMap = new HashMap<String, Object>();

    //创建一个线程池
    private static final ExecutorService executorService=Executors.newCachedThreadPool();

    public RPCServer(IRegistryCenter registryCenter, String serviceAddress) {
        this.registryCenter = registryCenter;
        this.serviceAddress = serviceAddress;
    }

    public void binding(Object... services){
        for (Object service : services){
            RPCAnnotaion rpcAnnotaion = service.getClass().getAnnotation(RPCAnnotaion.class);
            String serviceName = rpcAnnotaion.value().getSimpleName();
            String version = rpcAnnotaion.version();
            if (version != null && !version.equals("")){
                serviceName = serviceName + "-" + version;
            }

            handlerMap.put(serviceName,service);

        }
    }

    public void publisher(){

        ServerSocket serverSocket  = null;

        String [] addrs = serviceAddress.split(":");
        try {
            //启动一个服务监听
            serverSocket=new ServerSocket(Integer.parseInt(addrs[1]));


            for(String serviceName : handlerMap.keySet()) {

                registryCenter.registry(serviceName, serviceAddress);
                System.out.println("注册服务成功："+serviceName+"->"+serviceAddress);
            }

            //循环监听,所以负载均衡的demo需要两个main方法
            while(true){
                //监听服务
                Socket socket=serverSocket.accept();
                //通过线程池去处理请求
                executorService.execute(new ProcessorHandler(socket,handlerMap));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
