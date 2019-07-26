package com.sunyalong.client;

import com.sunyalong.read.SocketServerReadRunnable;
import com.sunyalong.write.SocketServerWriteRunnable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 客户端
 */
public class SocketClientDemo {

    private static ExecutorService threadPool = new ThreadPoolExecutor(2, 6,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());


    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入服务器IP: ");
        String serverIP = sc.next();

        System.out.println();

        System.out.print("请输入服务器端口: ");
        String serverPort = sc.next();

        Socket socket = new Socket(serverIP,Integer.parseInt(serverPort));

        SocketServerReadRunnable socketServerRunnable = new SocketServerReadRunnable(socket,"服务器: ");
        SocketServerWriteRunnable socketServerWriteRunnable = new SocketServerWriteRunnable(socket);
        threadPool.submit(socketServerRunnable);
        threadPool.submit(socketServerWriteRunnable);


    }

}
