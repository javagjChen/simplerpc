package com.cgj.rpc.server;

/**
 * @Classname FirstSerivceImpl
 * @Description TODO
 * @Date 2019/3/22 17:48
 * @Created by cgj
 */
@RPCAnnotaion(IFirstSevice.class)
public class FirstSerivceImpl implements IFirstSevice{
    @Override
    public void first(String name) {
        System.out.println("这是"+name+"的第一个用zk实现注册中心的RPC");
    }
}