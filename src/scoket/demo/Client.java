package scoket.demo;

import java.io.*;
import java.net.*;

/**
 * Socket Client
 */
public class Client {
    public static void main(String[] args) {
        Socket socket = new Socket();
        try {
            // 超时时间
            socket.setSoTimeout(3000);
            socket.connect(new InetSocketAddress(Inet4Address.getLocalHost(), 2000), 3000);
            System.out.println("connect success");
            System.out.println("client: " + socket.getLocalAddress() + " p: " + socket.getPort());
            System.out.println("server: " + socket.getInetAddress() + " p: " + socket.getPort());
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        todo(socket);
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void todo(Socket client){
        try {
            // 构建键盘输入流
            InputStream in = System.in;
            BufferedReader input = new BufferedReader(new InputStreamReader(in));

            OutputStream outputStream = client.getOutputStream();
            PrintStream socketPrintStream = new PrintStream(outputStream);

            InputStream inputStream = client.getInputStream();
            BufferedReader socketBufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            boolean flag = true;
            do {
                String str = input.readLine();
                socketPrintStream.println(str);

                String echo = socketBufferedReader.readLine();
                if ("bye".equalsIgnoreCase(echo)){
                    System.out.println("bye");
                    flag = false;
                }else{
                    System.out.println(echo);
                }
            } while (flag);
            socketBufferedReader.close();
            socketPrintStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
