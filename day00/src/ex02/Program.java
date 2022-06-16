package ex02;

import java.util.Scanner;

public class Program {
    public static boolean isPrime(int number) {
        for (int i = 2; i * i < number; i++) {
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

        long number = scanner.nextLong();

        int count = 0;

        if (number == 42) {
            System.out.println("Count of coffee-requests - 0");
            System.exit(0);
        } else {
            while (number != 42) {
                if (number <= 1) {
                    System.err.println("IllegalArgument");
                    System.exit(-1);
                }

                if (isSumPrime(number)) {
                    count++;
                }

                number = scanner.nextLong();
            }
        }

        System.out.println("Count of coffee-requests - " + count);
    }
}
