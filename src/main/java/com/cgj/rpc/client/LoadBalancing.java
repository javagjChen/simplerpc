package com.cgj.rpc.client;

import java.util.List;

/**
 * @Classname LoadBalancing
 * @Description TODO
 * @Date 2019/3/26 9:19
 * @Created by cgj
 */
public interface LoadBalancing {
    String selectOne(List<String> list);
}
