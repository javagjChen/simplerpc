package com.cgj.rpc.server.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * @Classname RegistryCenterImpl
 * @Description TODO
 * @Date 2019/3/22 10:04
 * @Created by Administrator
 */
public class RegistryCenterImpl implements IRegistryCenter {

    private CuratorFramework curatorFramework;

    {
        curatorFramework = CuratorFrameworkFactory.builder().
                connectString(ZkConfig.CONNETTING_STR).sessionTimeoutMs(4000).
                retryPolicy(new ExponentialBackoffRetry(1000,10)).build();

        curatorFramework.start();
    }

    @Override
    public void registry(String serviceName,String serviceAddress) {

        String servicePath = ZkConfig.BOOT_NODE + "/" + serviceName;
        try {

            if (curatorFramework.checkExists().forPath(servicePath) == null){
                curatorFramework.create().creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT).forPath(servicePath,"0".getBytes());
            }
            serviceAddress = servicePath + "/" + serviceAddress;
            String tNode = curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                    .forPath(serviceAddress,"1".getBytes());

            System.out.println("服务注册成功：" + tNode);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
