package com.cgj.rpc.client;

import com.cgj.rpc.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @Classname TCPTransport
 * @Description TODO
 * @Date 2019/3/24 17:31
 * @Created by cgj
 */
public class TCPTransport {

    private String address;

    public TCPTransport(String address) {
        this.address = address;
    }

    public Object send(RpcRequest rpcRequest){

        Socket socket = null;
        try{
            socket = newSocket();
            //获取输出流，将客户端需要调用的远程方法参数request发送给
            ObjectOutputStream outputStream=new ObjectOutputStream
                    (socket.getOutputStream());
            outputStream.writeObject(rpcRequest);
            outputStream.flush();
            //获取输入流，得到服务端的返回结果
            ObjectInputStream inputStream=new ObjectInputStream
                    (socket.getInputStream());
            Object result=inputStream.readObject();
            inputStream.close();
            outputStream.close();
            return result;
        }catch (Exception e){
            e.printStackTrace();
            //throw new RuntimeException("发起远程调用异常！" + e);
            return null;
        }finally {
            if(socket!=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //创建一个socket连接
    private Socket newSocket(){
        System.out.println("创建一个新的连接");
        Socket socket;
        try{
            String[] arrs=address.split(":");
            socket=new Socket(arrs[0],Integer.parseInt(arrs[1]));
            return socket;
        }catch (Exception e){
            throw new RuntimeException("连接建立失败");
        }
    }
}
