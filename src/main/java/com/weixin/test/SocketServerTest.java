package com.weixin.test;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Created by ZPC on 2017/8/10.
 */
public class SocketServerTest {
    public static void main(String[] args) throws IOException {

        /*byte[] bytes = new byte[1024];
        DatagramSocket datagramSocket = new DatagramSocket(10086);
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
        datagramSocket.receive(datagramPacket);
        System.out.println("********");
        System.out.println("********");
        byte[] data = datagramPacket.getData();
        int dataLent = datagramPacket.getLength();
        System.out.println(new String(data, 0, dataLent));
        System.out.println("********");
        System.out.println("********");

        datagramSocket.close();*/

        byte[] data = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(data, data.length);
        DatagramSocket datagramSocket = new DatagramSocket(10086);
        datagramSocket.receive(datagramPacket);
        System.out.println(new String(data, 0, datagramPacket.getLength()));

        datagramSocket.close();
    }
}

