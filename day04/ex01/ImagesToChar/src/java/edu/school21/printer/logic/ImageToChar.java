package edu.school21.printer.logic;

import edu.school21.printer.app.Program;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

public class ImageToChar {
    private char white;
    private char black;
    private char[][] charArray;

    public ImageToChar(char white, char black, URL file) {
        this.white = white;
        this.black = black;
        this.charArray = setCharArray(file, white, black);
    }

    public char[][] getCharArray() {
        return charArray;
    }

    public char[][] setCharArray(URL file, char white, char black) {
        try {
            BufferedImage buffer = ImageIO.read(ImageToChar.class.getResource("/resources/image.bmp"));
            char[][] array = new char[buffer.getHeight()][buffer.getWidth()];

            for (int y = 0; y < buffer.getHeight(); y++) {
                for (int x = 0; x < buffer.getWidth(); x++) {
                    int color = buffer.getRGB(x, y);

                    if (color == Color.WHITE.getRGB()) {
                        array[y][x] = white;
                    } else {
                        array[y][x] = black;
                    }
                }
            }

            return array;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
