package com.cgj.rpc.client;

import java.util.List;
import java.util.Random;

/**
 * @Classname RandomLoadBala
 * @Description TODO
 * @Date 2019/3/26 9:21
 * @Created by cgj
 */
public class RandomLoadBala implements LoadBalancing {
    @Override
    public String selectOne(List<String> list) {
        if (list == null){return null;}
        if (list.size() == 1){return list.get(0);}
        Integer len = list.size();
        Random random = new Random();
        return list.get(random.nextInt(len));

    }
}
