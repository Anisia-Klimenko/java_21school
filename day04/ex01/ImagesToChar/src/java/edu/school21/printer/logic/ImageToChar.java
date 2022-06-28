package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class ImageToChar {
    private char white;
    private char black;
    private char[][] charArray;

    public ImageToChar(char white, char black, String file) {
        this.white = white;
        this.black = black;
        this.charArray = setCharArray(file, white, black);
    }

    public char[][] getCharArray() {
        return charArray;
    }

    public char[][] setCharArray(String file, char white, char black) {
        try {
            BufferedImage buffer = ImageIO.read(new File(file));
            char[][] array = new char[buffer.getWidth()][buffer.getHeight()];

            for (int y = 0; y < buffer.getHeight(); y++) {
                for (int x = 0; x < buffer.getWidth(); x++) {
                    int color = buffer.getRGB(x, y);

                    if (color == Color.WHITE.getRGB()) {
                        array[x][y] = white;
                    } else {
                        array[x][y] = black;
                    }
                }
            }

            return array;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
