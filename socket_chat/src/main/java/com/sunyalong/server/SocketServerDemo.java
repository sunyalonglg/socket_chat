package com.sunyalong.server;

import com.sunyalong.guard.GuardConsoleReadParamRunnable;
import com.sunyalong.read.SocketServerReadRunnable;
import com.sunyalong.write.SocketServerWriteRunnable;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;

/**
 * 服务器端程序
 *
 */
public class SocketServerDemo {

    private static ExecutorService threadPool = new ThreadPoolExecutor(100, 101,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    private volatile static HashMap<Integer,Socket> socketMap = new HashMap<>();

    private static Integer id = 0;

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(1000);

        GuardConsoleReadParamRunnable guardConsoleReadParamRunnable = new GuardConsoleReadParamRunnable(serverSocket,socketMap);
        // 后期更改为建立守护线程
        threadPool.submit(guardConsoleReadParamRunnable);

        while (true) {
            Socket socket = serverSocket.accept();
            SocketServerReadRunnable socketServerReadRunnable = new SocketServerReadRunnable(socket,"客户端: ");
            threadPool.submit(socketServerReadRunnable);
            Integer channlId = getId();
            socketMap.put(channlId,socket);


            System.out.println("连接检查完成连接通道ID: "+channlId);
        }











    }


    /**
     * 生成通道ID
     *
     * @return
     */
    private static synchronized Integer getId(){
        id = id+1;
        return id;
    }

}
