package ex05;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        boolean isDev = false;

        if (args.length != 1) {
            System.err.println("Specify a launch mode (dev/production): --profile=_");
            System.exit(-1);
        }

        if (args[0].equals("--profile=dev")) {
            isDev = true;
        }

        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);
        Integer elem;

        System.out.println(" ");

        while (true) {
            menu.showMenu();
            System.out.print("-> ");

            try {
                elem = Integer.parseInt(scanner.nextLine());

                switch (elem) {
                    case (1):
                        System.out.print("Enter a user name and a balance\n-> ");
                        menu.addUser(scanner.nextLine());
                        break;
                    case (2):
                        System.out.print("Enter a user ID\n-> ");
                        menu.getBalance(scanner.nextLine());
                        break;
                    case (3):
                        System.out.print("Enter a recipient ID, a sender ID and a transfer amount\n-> ");
                        menu.makeTransfer(scanner.nextLine());
                        break;
                    case (4):
                        System.out.print("Enter a user ID\n-> ");
                        menu.getUserTransactions(scanner.nextLine());
                        break;
                    case (5):
                        if (isDev) {
                            System.out.print("Enter a user ID and a transfer ID\n-> ");
                            menu.removeUserTransaction(scanner.nextLine());
                        } else {
                            System.out.println("Permission denied");
                        }
                        break;
                    case (6):
                        if (isDev) {
                            menu.checkTransactionCorrect();
                        } else {
                            System.out.println("Permission denied");
                        }
                        break;
                    case (7):
                        System.out.println("Finishing execution");
                        System.exit(0);
                    default:
                        System.out.println("Try again...");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println("---------------------------------------------------------");
        }
    }
}
