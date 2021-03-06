package com.cgj.rpc.server;

/**
 * @Classname FirstSerivceImpl
 * @Description TODO
 * @Date 2019/3/22 17:48
 * @Created by cgj
 */
@RPCAnnotaion(IFirstService.class)
public class FirstServiceImpl2 implements IFirstService {
    @Override
    public String first(String name) {

        return "这是"+name+"的第一个用zk实现注册中心的RPC，测试负载均衡";
    }
}
