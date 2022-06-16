package ex03;

import java.util.Scanner;

public class Program {
    public static final int MIN_MARK = 1;
    public static final int MAX_MARK = 9;
    public static final int NUM_WEEK = 18;
    public static final int NUM_MARK = 5;

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
        int scanInt = 0;
        long minMark = MAX_MARK;
        int scanMark = 0;
        long result = 0;

        String string = scanner.next();

        if (string.equals("42")) {
            System.exit(0);
        } else {
            while (!string.equals("42") && weekNum < NUM_WEEK) {
                scanInt = scanner.nextInt();

                if (!string.equals("Week") || scanInt != weekNum + 1) {
                    System.err.println("IllegalArgument");
                    System.exit(-1);
                }

                for (int i = 0; i < NUM_MARK; i++) {
                    scanMark = scanner.nextInt();

                    if (scanMark < MIN_MARK || scanMark > MAX_MARK) {
                        System.err.println("IllegalArgument");
                        System.exit(-1);
                    }

                    if (scanMark < minMark) {
                        minMark = scanMark;
                    }
                }

                result = result * 10 + minMark;
                weekNum++;
                minMark = 10;
                string = scanner.next();
            }
        }

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
