package scoket.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(2000);

        System.out.println("server already");
        System.out.println("server: " + serverSocket.getInetAddress() + " p: " + serverSocket.getLocalPort());

        for (;;){
            Socket client = serverSocket.accept();
            ClientHandler handler = new ClientHandler(client);
            handler.start();
        }
    }

    private static class ClientHandler extends Thread{
        private Socket socket;
        private boolean flag = true;

        ClientHandler(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            super.run();
            System.out.println("client: " + socket.getInetAddress() + " p: " + socket.getPort());

            try {
                PrintStream socketOutput = new PrintStream(socket.getOutputStream());
                BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                do {
                    String str = socketInput.readLine();
                    if ("bye".equalsIgnoreCase(str)){
                        socketOutput.println("bye");
                        flag = false;
                    }else {
                        System.out.println(str);
                        socketOutput.println("length: " + str.length());
                    }
                }while (flag);

                socketInput.close();
                socketOutput.close();
            }catch (Exception e){
                System.out.println("connect error");
            }finally {
                try {
                    if (socket != null){
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("socket connect out");
        }
    }
}
