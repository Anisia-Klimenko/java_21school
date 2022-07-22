package ex02;

import java.util.Scanner;

public class Program {
    public static final int LAST_ELEMENT = 42;

    public static boolean isPrime(int number) {
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static boolean isSumPrime(long number) {
        int sum = 0;

        while (number > 0) {
            sum += number % 10;
            number = number / 10;
        }

        return isPrime(sum);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("-> ");

        long number = scanner.nextLong();

        int count = 0;

        if (number == LAST_ELEMENT) {
            System.out.println("   Count of coffee-requests - 0");
            scanner.close();
            System.exit(0);
        } else {
            while (number != LAST_ELEMENT) {
                if (number <= 1) {
                    System.err.println("   IllegalArgument");
                    scanner.close();
                    System.exit(-1);
                }

                if (isSumPrime(number)) {
                    count++;
                }

                System.out.print("-> ");
                number = scanner.nextLong();
            }
        }

        System.out.println("   Count of coffee-requests - " + count);
    }
}
