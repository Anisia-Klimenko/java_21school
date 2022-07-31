package edu.school21.sockets.server;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepositoryImpl;
import edu.school21.sockets.services.UsersServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Optional;

public class ClientHandler implements Runnable {
    private Socket socket;
    private OutputStreamWriter writer;
    private BufferedReader reader;
    private AnnotationConfigApplicationContext context;
    private static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private String userName;

    public ClientHandler(Socket socket, AnnotationConfigApplicationContext context) {
        try {
            this.socket = socket;
            this.writer = new OutputStreamWriter(socket.getOutputStream());
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.context = context;
            clientHandlers.add(this);
        } catch (IOException e) {
            closeConnections(socket, writer, reader);
        }

    }

    @Override
    public void run() {
        try {
            String response = postGet("Hello from Server!", writer, reader);
            boolean exitStatus = true;

            while (exitStatus) {
                System.out.println(response);
                switch (response.toLowerCase()) {
                    case "signup":
                        response = signUpClient(reader, writer);
                        break;
                    case "signin":
                        response = signInClient(reader, writer);
                        if (response.equals("Invalid username!")) {
                            response = postGet(response, writer, reader);
                            exitStatus = false;
                        } else if (response.equals("Invalid password!")) {
                            response = postGet(response, writer, reader);
                        } else {
                            response = "message";
                        }
                        break;
                    case "message":
                        response = startMessaging(response, reader, writer);
                    case "exit":
                        postGet("You have left the chat.", writer, reader);
                        exitStatus = false;
                        break;
                    default:
                        response = postGet("Invalid command!", writer, reader);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String  signUpClient(BufferedReader reader, OutputStreamWriter writer) throws IOException {
        UsersServiceImpl usersService = context.getBean("usersServiceImpl", UsersServiceImpl.class);

        String userName = postGet("Enter username:", writer, reader);
        String password = postGet("Enter password:", writer, reader);

        usersService.signUp(userName, password);

        return postGet("Successful! Before start messaging you need to sign in", writer, reader);
    }

    public String signInClient(BufferedReader reader, OutputStreamWriter writer) throws IOException {
        UsersRepositoryImpl usersRepository = context.getBean("usersRepositoryImpl", UsersRepositoryImpl.class);
        UsersServiceImpl usersService = context.getBean("usersServiceImpl", UsersServiceImpl.class);

        String userName = postGet("Enter username:", writer, reader);
        if (!usersRepository.findByUserName(userName).isPresent()) {
            return "Invalid username!";
        }
        String password = postGet("Enter password:", writer, reader);

        Optional<User> optionalUser = usersRepository.findByUserName(userName);

        if (!optionalUser.filter(user -> usersService.getPasswordEncoder().matches(password, user.getPassword())).isPresent()) {
            return "Invalid password!";
        }

        this.userName = userName;

        return "success!";
    }

    public String startMessaging(String response, BufferedReader reader, OutputStreamWriter writer) throws IOException {
        response = postGet("Start messaging", writer, reader);
        String message;

        while (socket.isConnected()) {
            try {
                message = reader.readLine();
                broadCastMessage(message);
            } catch (IOException e) {
                 closeConnections(socket, writer, reader);
                 break;
            }
        }

//        while (!response.toLowerCase().equals("exit")) {
//            response = postGet("", writer, reader);
//        }

        return response;
    }

    public void broadCastMessage(String message) {
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                clientHandler.writer.write(userName + ": " + message + "\n");
                clientHandler.writer.flush();
            } catch (IOException e) {
                closeConnections(socket, writer, reader);
            }
        }
    }

    public void removeClientHandler() {
        clientHandlers.remove(this);
        broadCastMessage("has left the chat!");
    }

    private String postGet(String message, OutputStreamWriter writer, BufferedReader reader) throws IOException {
        writer.write(message + "\n");
        writer.flush();

        return reader.readLine();
    }

    public void closeConnections (Socket socket, OutputStreamWriter writer, BufferedReader reader) {
        removeClientHandler();

        try {
            if (socket != null) {
                socket.close();
            }
            if (writer != null) {
                writer.close();
            }
            if (reader != null) {
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
