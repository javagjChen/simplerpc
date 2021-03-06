package com.cgj.rpc.client.zk;

import com.cgj.rpc.client.LoadBalancing;
import com.cgj.rpc.client.RandomLoadBala;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.List;

/**
 * @Classname DiscoveryImpl
 * @Description TODO
 * @Date 2019/3/22 16:10
 * @Created by Administrator
 */
public class DiscoveryImpl implements IDiscovery {

    private List<String> pas = null;

    private CuratorFramework curatorFramework;

    {
        curatorFramework = CuratorFrameworkFactory.builder().connectString(ZkConfig.CONNECTING_STR).sessionTimeoutMs(4000)
                .retryPolicy(new ExponentialBackoffRetry(1000,10)).build();
        curatorFramework.start();
    }


    @Override
    public String discovery(String serviceName) {
        String servicePath = ZkConfig.BOOT_NODE + "/" + serviceName;
        try {
            pas = curatorFramework.getChildren().forPath(servicePath);

            //监听子节点的变化
            whaterChildren(servicePath);

            //随机负载均衡
            LoadBalancing lb = new RandomLoadBala();

            return lb.selectOne(pas);
        } catch (Exception e) {

            e.printStackTrace();
            throw new RuntimeException("发现服务异常" + e);
        }
    }

    //动态监听子节点的变化
    private void whaterChildren(final String servicePath) {

        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework,servicePath,true);

        PathChildrenCacheListener pathChildrenCacheListener = new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                pas = curatorFramework.getChildren().forPath(servicePath);
            }
        };
        pathChildrenCache.getListenable().addListener(pathChildrenCacheListener);
        try {
            pathChildrenCache.start();
        } catch (Exception e) {
            throw new RuntimeException("监听子节点变化的watcher事件异常" + e);
        }
    }
}
