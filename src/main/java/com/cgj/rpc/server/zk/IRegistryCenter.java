package com.cgj.rpc.server.zk;

/**
 * @Classname IRegistryCenter
 * @Description TODO
 * @Date 2019/3/22 10:03
 * @Created by Administrator
 */
public interface IRegistryCenter {

    /**
     * 注册方法
     */
    void registry (String serviceName,String serviceAddress);
}
