package edu.school21.sockets.server;

import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.services.UsersService;
import edu.school21.sockets.services.UsersServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
            String userName = "";
            String password = "";
            String signUp = "";
            UsersServiceImpl usersService = context.getBean("usersServiceImpl", UsersServiceImpl.class);
            Socket clientSocket = serverSocket.accept();


            System.out.println("Hello from Server!");

            OutputStreamWriter writer = new OutputStreamWriter(clientSocket.getOutputStream());
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream())
            );

            signUp = saveSend("Hello from Server!", writer, reader);
            while (!signUp.equals("signUp")) {
                signUp = saveSend("signUp first!", writer, reader);
            }

            userName = saveSend("Enter username:", writer, reader);
            password = saveSend("Enter password:", writer, reader);

            System.out.println(userName + " " + password);

            if (usersService.getPasswordEncoder().matches(password, usersService.signUp(userName, password))) {
                saveSend("Successful!\n", writer, reader);
            } else {
                saveSend("Failure!", writer, reader);
            }

            reader.close();
            writer.close();
            clientSocket.close();

        } catch (IOException e) {
            System.err.println("An error has occurred!");
            System.exit(1);
        }
    }

    private String saveSend(String message, OutputStreamWriter writer, BufferedReader reader) throws IOException {
        writer.write(message + "\n");
        writer.flush();

        return reader.readLine();
    }

}
