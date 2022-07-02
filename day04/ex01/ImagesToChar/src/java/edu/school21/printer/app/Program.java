package edu.school21.printer.app;

import edu.school21.printer.logic.ImageToChar;

public class Program {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Should be 2 arguments: whiteChar blackChar");
            System.exit(-1);
        }

        ImageToChar imageToChar = new ImageToChar(args[0].charAt(0), args[1].charAt(0),
                Program.class.getResource("resources/image.bmp"));
        char[][] array = imageToChar.getCharArray();

        for (char[] line : array) {
            System.out.println(line);
        }
    }
}
