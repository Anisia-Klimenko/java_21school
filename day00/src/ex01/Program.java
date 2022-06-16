package ex01;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        int steps = 1;

        Scanner scanner = new Scanner(System.in);

        int number = scanner.nextInt();

        if (number <= 1) {
            System.err.println("IllegalArgument");
            System.exit(-1);
        }

        for (int i = 2; i * i < number; i++) {
            if (number % i == 0) {
                System.out.println("false " + steps);
                System.exit(0);
            } else {
                steps++;
            }
        }

        System.out.println("true " + steps);
        System.exit(0);
    }
}
