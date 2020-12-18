package scoket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.SocketException;

public class UDPSearcher {
    public static void main(String[] args) throws IOException {
        System.out.println("UDPSearcher Started.");

        // 作为接受者，指定一个端口用于数据接收
        DatagramSocket ds = new DatagramSocket();

        // 构建一份回送数据
        String responseData = "hello world";
        byte[] responseDataBytes = responseData.getBytes();
        DatagramPacket responsePacket = new DatagramPacket(responseDataBytes,
                responseDataBytes.length);
        responsePacket.setAddress(Inet4Address.getLocalHost());
        responsePacket.setPort(20000);
        ds.send(responsePacket);

        // 构建接收实体
        final byte[] buf = new byte[512];
        DatagramPacket receivePack = new DatagramPacket(buf, buf.length);

        // 接受
        ds.receive(receivePack);

        String ip = receivePack.getAddress().getHostAddress();
        int port = receivePack.getPort();
        int dataLen = receivePack.getLength();
        String data = new String(receivePack.getData(), dataLen);
        System.out.println("UDPSearcher receive from ip: " + ip + "\tport: " + port + "\tdata: " + data);

        System.out.println("UDPSearcher finished");
        ds.close();
    }
}
