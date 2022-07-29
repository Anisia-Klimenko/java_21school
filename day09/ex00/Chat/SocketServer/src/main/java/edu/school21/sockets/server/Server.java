package edu.school21.sockets.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Socket client = serverSocket.accept();
            System.out.print("Connection accepted.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
