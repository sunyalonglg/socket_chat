package com.sunyalong.read;

import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 服务器读取线程
 */
public class SocketServerReadRunnable implements Runnable {

    String serverName = null;
    Socket socket = null;
    InputStream in = null;
    Scanner sc = new Scanner(System.in);


    public SocketServerReadRunnable(Socket socket,String serverName){
        if (socket == null) {
            throw new RuntimeException("socket 连接为空");
        }

        try {
            this.serverName = serverName;
            this.socket = socket;
            this.in = socket.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    @Override
    public void run() {
        try {
            while (true) {
                byte[] dataBuff = new byte[1024];
                int n = in.read(dataBuff);
                if (n > 0) {
                    System.out.println(serverName + new String(dataBuff, 0, n,"utf-8"));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                sc.close();
                in.close();
                socket.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }
}
