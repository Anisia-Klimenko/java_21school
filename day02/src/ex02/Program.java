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
        Command command = new Command(currentDir);

        while (!line[0].equals("42") && !line[0].isEmpty()) {
            try {
                switch (line[0]) {
                    case "ls":
                        command.ls();
                        break;
                    case "mv":
                        command.mv(line[1], line[2]);
                        break;
                    case "cd":
                        command.cd(Paths.get(line[1]));
                        break;
                    case "exit":
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Command not found, \"exit\" or \"Enter\" to exit program");
                        break;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.print(" -> ");
            line = scanner.nextLine().split("\\s+");
        }
    }
}
