package edu.school21.sockets.app;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

@Parameters(separators = "=")
public class Main {
    @Parameter(names = {"--server-port", "-p"})
    int port;
    public static void main(String[] args) {
        Main main = new Main();

        JCommander.newBuilder().addObject(main).build().parse(args);
        main.run();
    }

    public void run() {
        try (Socket clientSocket = new Socket("127.0.0.1", port)) {
            OutputStreamWriter writer = new OutputStreamWriter(clientSocket.getOutputStream());
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream())
            );
            Scanner scanner = new Scanner(System.in);
            String response = reader.readLine();

            while (!response.equalsIgnoreCase("exit")) {
                switch (response.toLowerCase()) {
                    case "signin":
                        break;
                    case "invalid password!":
                        showPost(response + "\nSign in again!", writer, scanner);
                        response = reader.readLine();
                        break;
                    case "invalid username!":
                        System.err.println(response);
                        response = "exit";
                        writer.write("exit\n");
                        writer.flush();
                        break;
                    case "you have left the chat.":
                        System.out.println(response);
                        response = "exit";
                        break;
                    case "message":
                        listenForMessage(clientSocket, reader, writer);
                        response = sendMessage(scanner, clientSocket, writer, reader);
                        break;
                    default:
                        showPost(response, writer, scanner);
                        response = reader.readLine();
                        break;
                }
            }

            reader.close();
            writer.close();
            scanner.close();

        } catch (IOException e) {
            System.err.println("An error has occurred!");
            System.exit(1);
        }
    }

    public String manageMessages(Scanner scanner, Socket socket, OutputStreamWriter writer, BufferedReader reader) {
        listenForMessage(socket, reader, writer);
        sendMessage(scanner, socket, writer, reader);
        return "exit";
    }

    private void showPost(String message, OutputStreamWriter writer, Scanner scanner) throws IOException {
        System.out.println(message);
        System.out.print("> ");

        writer.write(scanner.nextLine() + "\n");
        writer.flush();
    }

    public String sendMessage(Scanner scanner, Socket socket, OutputStreamWriter writer, BufferedReader reader) {
        String message = "";

        try {
            while (socket.isConnected() && !message.equalsIgnoreCase("exit")) {
                message = scanner.nextLine();
                writer.write(message);
                writer.flush();
            }
        } catch (IOException e) {
            closeConnections(socket, writer, reader);
            return "exit";
        }
        return "exit";
    }

    public void listenForMessage(Socket socket, BufferedReader reader, OutputStreamWriter writer) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String chatMessage;

                while (socket.isConnected()) {
                    try {
                        chatMessage = reader.readLine();
                        System.out.println(chatMessage);
                    } catch (IOException e) {
                         closeConnections(socket, writer, reader);
                    }
                }
            }
        }).start();
    }

    public void closeConnections (Socket socket, OutputStreamWriter writer, BufferedReader reader) {
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
