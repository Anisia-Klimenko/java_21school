package ex03;

import java.util.Scanner;

public class Program {
    public static final int MIN_MARK = 1;
    public static final int MAX_MARK = 9;
    public static final int NUM_WEEK = 18;
    public static final int NUM_OF_TESTS = 5;
    public static final String INPUT_DATA_LIMIT = "42";

    public static long getFirstNumber(int weekNum, int currentWeek, long result) {
        for (int i = 0; i < weekNum - currentWeek - 1; i++) {
            result = result / 10;
        }

        return result;
    }

    public static long removeFirstNumber(int weekNum, int currentWeek, long result) {
        long number = 1;

        for (int i = 0; i < weekNum - currentWeek - 1; i++) {
            number *= 10;
        }

        return result % number;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int weekNum = 0;

        int scanInt;

        int scanMark;

        long minMark = MAX_MARK;

        long result = 0;

        System.out.print("-> ");
        String scanString = scanner.next();

        if (scanString.equals(INPUT_DATA_LIMIT)) {
            System.exit(0);
        } else {
            while (!scanString.equals(INPUT_DATA_LIMIT) && weekNum < NUM_WEEK) {
                scanInt = scanner.nextInt();

                if (!scanString.equals("Week") || scanInt != weekNum + 1) {
                    System.err.println("IllegalArgument");
                    scanner.close();
                    System.exit(-1);
                }

                System.out.print("-> ");

                for (int i = 0; i < NUM_OF_TESTS; i++) {
                    scanMark = scanner.nextInt();

                    if (scanMark < MIN_MARK || scanMark > MAX_MARK) {
                        System.err.println("IllegalArgument");
                        scanner.close();
                        System.exit(-1);
                    }

                    if (scanMark < minMark) {
                        minMark = scanMark;
                    }
                }

                result = result * 10 + minMark;
                weekNum++;
                minMark = 10;
                System.out.print("-> ");
                scanString = scanner.next();
            }
        }

        scanner.close();

        for (int i = 0; i < weekNum; i++) {
            System.out.print("Week ");
            System.out.print(i + 1);
            System.out.print(" ");

            minMark = getFirstNumber(weekNum, i, result);

            for (int j = 0; j < minMark; j++) {
                System.out.print("=");
            }

            result = removeFirstNumber(weekNum, i, result);
            System.out.print(">\n");
        }

    }
}
