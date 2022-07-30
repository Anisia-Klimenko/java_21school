package edu.school21.sockets.server;

import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepositoryImpl;
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
import java.util.Locale;
import java.util.Optional;

public class Server {
    private int port;
    private AnnotationConfigApplicationContext context;

    public Server(int port) {
        this.port = port;
        this.context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Socket clientSocket = serverSocket.accept();
            OutputStreamWriter writer = new OutputStreamWriter(clientSocket.getOutputStream());
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream())
            );
            System.out.println("Client has connected!");

            String response = saveSend("Hello from Server!", writer, reader);
            boolean exitStatus = true;

            while (exitStatus) {
                switch (response.toLowerCase()) {
                    case "signup":
                        signUpClient(reader, writer);
                        break;
                    case "signin":
                        if (signInClient(reader, writer)) {
                            response = startMessaging(reader, writer);
                        } else {
                            response = saveSend("Invalid username or password!", writer, reader);
                            exitStatus = false;
                        }
                        break;
                    case "exit":
                        exitStatus = false;
                        saveSend("You have left the chat.", writer, reader);
                        break;
                    default:
                        response = saveSend("Invalid command!", writer, reader);
                        break;
                }
            }

            clientSocket.close();
            reader.close();
            writer.close();
        } catch (IOException e) {
            System.err.println("An error has occurred!");
            System.exit(1);
        }
    }

    public boolean signInClient(BufferedReader reader, OutputStreamWriter writer) throws IOException {
        UsersRepositoryImpl usersRepository = context.getBean("usersRepositoryImpl", UsersRepositoryImpl.class);
        UsersServiceImpl usersService = context.getBean("usersServiceImpl", UsersServiceImpl.class);

        String userName = saveSend("Enter username:", writer, reader);
        String password = saveSend("Enter password:", writer, reader);

        Optional<User> optionalUser = usersRepository.findByUserName(userName);
        return optionalUser.filter(user -> usersService.getPasswordEncoder().matches(password, user.getPassword())).isPresent();
    }

    public String startMessaging(BufferedReader reader, OutputStreamWriter writer) throws IOException {
        String response = "";

        while (!response.toLowerCase().equals("exit")) {
            response = saveSend("", writer, reader);
        }

        return response;
    }

    public void signUpClient(BufferedReader reader, OutputStreamWriter writer) throws IOException {
        UsersServiceImpl usersService = context.getBean("usersServiceImpl", UsersServiceImpl.class);

        String userName = saveSend("Enter username:", writer, reader);
        String password = saveSend("Enter password:", writer, reader);

        usersService.signUp(userName, password);
        writer.write("Successful!\n");
        writer.flush();
    }

    private String saveSend(String message, OutputStreamWriter writer, BufferedReader reader) throws IOException {
        writer.write(message + "\n");
        writer.flush();

        return reader.readLine();
    }

}
