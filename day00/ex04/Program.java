package ex04;

import java.util.Scanner;

public class Program {
    public static final int MAX_PRINT_SYMBOLS = 10;
    public static final int SCALE = 10;

    public static int indexInArrayOfSymbols(char[] arrayOfSymbols, char symbol) {
        for (int i = 0; i < arrayOfSymbols.length; i++) {
            if (arrayOfSymbols[i] == symbol) {
                return i;
            }
        }

        return -1;
    }

    public static char[] newArrayOfSymbols(char[] arrayOfSymbols, char newSymbol) {
        char[] newArrayOfSymbols = new char[arrayOfSymbols.length + 1];

        for (int i = 0; i < arrayOfSymbols.length; i++) {
            newArrayOfSymbols[i] = arrayOfSymbols[i];
        }

        newArrayOfSymbols[arrayOfSymbols.length] = newSymbol;

        return newArrayOfSymbols;
    }

    public static int[] scaleArrayOfQuantity(int[] arrayOfQuantity) {
        int currentMaxPrintSymbols = arrayOfQuantity.length > MAX_PRINT_SYMBOLS ? MAX_PRINT_SYMBOLS : arrayOfQuantity.length;

        int[] scaledArrayOfQuantity = new int[currentMaxPrintSymbols];

        int maxQuantity = arrayOfQuantity[0];

        for (int i = 0; i < currentMaxPrintSymbols; i++) {
            scaledArrayOfQuantity[i] = arrayOfQuantity[i] * SCALE / maxQuantity;
        }

        return scaledArrayOfQuantity;
    }

    public static void printDiagram(char[] arrayOfSymbols, int[] arrayOfQuantity, int[] scaledArrayOfQuantity) {
        int currentMaxPrintSymbols = arrayOfQuantity.length > MAX_PRINT_SYMBOLS ? MAX_PRINT_SYMBOLS : arrayOfQuantity.length;

        for (int i = 0; i < SCALE + 1; i++) {
            for (int j = 0; j < currentMaxPrintSymbols; j++) {
                if (scaledArrayOfQuantity[j] == SCALE - i) {
                    if (arrayOfQuantity[j] / 10 == 0) {
                        System.out.print(" ");
                        System.out.print(arrayOfQuantity[j]);
                        System.out.print(" ");
                    } else if (arrayOfQuantity[j] / 100 == 0) {
                        System.out.print(arrayOfQuantity[j]);
                        System.out.print(" ");
                    } else {
                        System.out.print(arrayOfQuantity[j]);
                    }
                } else if (scaledArrayOfQuantity[j] > SCALE - i) {
                    System.out.print(" # ");
                }
            }

            System.out.println(" ");
        }

        System.out.print(" ");

        for (int i = 0; i < currentMaxPrintSymbols; i++) {
            System.out.print(arrayOfSymbols[i]);
            System.out.print("  ");
        }

        System.out.println(" ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextLine()) {
            System.err.println("IllegalArgument");
            scanner.close();
            System.exit(-1);
        }

        char[] arrayOfChars = scanner.nextLine().toCharArray();

        scanner.close();

        if (arrayOfChars.length == 0) {
            System.exit(0);
        }

        char[] arrayOfSymbols = {arrayOfChars[0]};

        int index;

        for (char ch : arrayOfChars) {
            index = indexInArrayOfSymbols(arrayOfSymbols, ch);

            if (index < 0) {
                arrayOfSymbols = newArrayOfSymbols(arrayOfSymbols, ch);
            }
        }

        int[] arrayOfQuantity = new int[arrayOfSymbols.length];

        for (char ch : arrayOfChars) {
            index = indexInArrayOfSymbols(arrayOfSymbols, ch);
            arrayOfQuantity[index]++;
        }

        int tempInt;

        char tempChar;

        for (int i = 0; i < arrayOfSymbols.length; i++) {
            for (int j = i; j < arrayOfSymbols.length; j++) {
                if (arrayOfQuantity[j] > arrayOfQuantity[i] || (arrayOfQuantity[j] == arrayOfQuantity[i]
                        && arrayOfSymbols[i] > arrayOfSymbols[j])) {
                    tempInt = arrayOfQuantity[j];
                    arrayOfQuantity[j] = arrayOfQuantity[i];
                    arrayOfQuantity[i] = tempInt;
                    tempChar = arrayOfSymbols[j];
                    arrayOfSymbols[j] = arrayOfSymbols[i];
                    arrayOfSymbols[i] = tempChar;
                }
            }
        }

        int[] scaledArrayOfQuantity = scaleArrayOfQuantity(arrayOfQuantity);

        printDiagram(arrayOfSymbols, arrayOfQuantity, scaledArrayOfQuantity);
    }
}
