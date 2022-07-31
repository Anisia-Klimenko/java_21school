package edu.school21.sockets.server;

import edu.school21.sockets.config.SocketsApplicationConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;
    private AnnotationConfigApplicationContext context;

    public Server(int port) {
        this.port = port;
        this.context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            while (!serverSocket.isClosed()) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client has connected!");

                ClientHandler clientHandler = new ClientHandler(clientSocket, context);
                Thread thread = new Thread(clientHandler);
                thread.start();


            }

//            closeConnections(clientSocket, writer, reader);
        } catch (IOException e) {
            System.err.println("An error has occurred!");
            System.exit(1);
        }
    }




}
