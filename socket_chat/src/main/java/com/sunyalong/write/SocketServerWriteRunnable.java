package com.sunyalong.write;

import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 服务器写线程
 *
 */
public class SocketServerWriteRunnable implements Runnable {

    Socket socket = null;
    OutputStream out = null;
    Scanner sc = new Scanner(System.in);


    public SocketServerWriteRunnable(Socket socket){
        if (socket == null) {
            throw new RuntimeException("socket 连接为空");
        }

        try {
            this.socket = socket;
            this.out = socket.getOutputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    @Override
    public void run() {
        try {
            while (true) {
                // 获得键盘输入
                String next = sc.next();
                if (next.equals("exit")) {
                    break;
                }
                out.write(next.getBytes());
                out.flush();
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                sc.close();
                out.close();
                socket.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }
}
