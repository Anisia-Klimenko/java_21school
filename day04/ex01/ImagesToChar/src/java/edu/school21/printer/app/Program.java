package edu.school21.printer.app;

import edu.school21.printer.logic.ImageToChar;

public class Program {
    public static void main(String[] args) {
//        if (args.length != 3) {
//            System.err.println("Should be 3 arguments: whiteChar blackChar BMPFilePath");
//            System.exit(-1);
//        }
//
//        ImageToChar imageToChar = new ImageToChar(args[0].charAt(0), args[1].charAt(0), args[3]);
        ImageToChar imageToChar = new ImageToChar('.','0', "test.bmp");
        char[][] array = imageToChar.getCharArray();
//        char[][] array2 = new char[3][3];
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                array2[i][j] = '.';
//            }
//        }
        for (char[] line : array) {
            System.out.println(line);
        }
    }
}
