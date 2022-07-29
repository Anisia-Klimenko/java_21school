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
    @Parameter(names = {"--port", "-p"})
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
            String input = reader.readLine();

            while (!input.equals("Enter password:")) {
                showSend(input, writer, scanner);
                input = reader.readLine();
            }

            showSend(input, writer, scanner);

            reader.close();
            writer.close();
            scanner.close();

        } catch (IOException e) {
            System.err.println("An error has occurred!");
            System.exit(1);
        }
    }

    private void showSend(String message, OutputStreamWriter writer, Scanner scanner) throws IOException {
        System.out.println(message);
        System.out.print("> ");

        writer.write(scanner.nextLine() + "\n");
        writer.flush();
    }
}
