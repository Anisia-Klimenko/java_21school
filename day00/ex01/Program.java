package ex01;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        int steps = 1;

        Scanner scanner = new Scanner(System.in);
        System.out.print("-> ");

        int number = scanner.nextInt();

        if (number <= 1) {
            System.err.println("   IllegalArgument");
            scanner.close();
            System.exit(-1);
        }

        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                System.out.println("   false " + steps);
                scanner.close();
                System.exit(0);
            } else {
                steps++;
            }
        }

        System.out.println("   true " + steps);
        scanner.close();
        System.exit(0);
    }
}
