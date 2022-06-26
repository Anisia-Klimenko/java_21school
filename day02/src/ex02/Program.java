package ex02;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Should be an argument: --current-folder=_");
            System.exit(-1);
        }

        if (!args[0].startsWith("--current-folder=")) {
            System.err.println("Should be an argument: --current-folder=_");
            System.exit(-1);
        }

        Path currentDir = Paths.get(args[0].substring(17));
        Scanner scanner = new Scanner(System.in);
        System.out.print(currentDir + "\n -> ");
        String[] line = scanner.nextLine().split("\\s+");

        while (!line[0].equals("42") && !line[0].isEmpty()) {
            Command command = new Command(currentDir);
            switch (line[0]) {
                case "ls":
                    command.ls();
                    break;
                case "mv":
                    command.mv();
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Command not found, \"exit\" to exit program");
                    break;
            }

            System.out.print(" -> ");
            line = scanner.nextLine().split("\\s+");
        }
    }
}
