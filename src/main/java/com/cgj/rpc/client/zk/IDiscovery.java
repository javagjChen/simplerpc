package com.cgj.rpc.client.zk;

/**
 * @Classname IDiscovery
 * @Description TODO
 * @Date 2019/3/22 16:05
 * @Created by Administrator
 */
public interface IDiscovery {

    /**
     * 发现服务
     * @param serviceName
     * @return
     */
    String discovery(String serviceName);
}
