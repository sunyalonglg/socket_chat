package com.sunyalong.guard;

import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * 守护线程守护主进程读取控制台输入的文字
 *
 */
public class GuardConsoleReadParamRunnable implements Runnable {

    ServerSocket serverSocket;
    Scanner sc = new Scanner(System.in);
    HashMap<Integer,Socket> socketMap = null;

    public GuardConsoleReadParamRunnable(ServerSocket serverSocket,HashMap<Integer,Socket> socketMap){
        this.serverSocket= serverSocket;
        this.socketMap=socketMap;
    }


    @Override
    public void run() {

        try {
            while (true) {
                // 获得键盘输入
                String next = sc.next();

                if ("".equals(next)) {
                    continue;
                }
                if (next.equals("exit")) {
                    break;
                }

                String[] outputStr = next.split(":");
                if (outputStr.length < 2) {
                    System.out.println("字符格式不正确, 请输入回复的 通道ID:内容");
                    continue;
                }

                Integer integer = -1;

                try {
                     integer = Integer.valueOf(outputStr[0]);
                } catch (ClassCastException e) {
                    System.out.println("通道名称不正确, 通道ID:内容");
                    continue;
                }

                // 判断输入的通道编号是否存在与通道集合中
                Socket sendSocket = socketMap.get(integer);
                if (sendSocket != null) {
                    int maoIndex = next.indexOf(":");
                    String reply = next.substring(maoIndex+1);
                    OutputStream outputStream = sendSocket.getOutputStream();
                    outputStream.write(reply.getBytes());
                    outputStream.flush();
                }else{
                    System.out.println("通道名称不正确");
                    continue;
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                sc.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
