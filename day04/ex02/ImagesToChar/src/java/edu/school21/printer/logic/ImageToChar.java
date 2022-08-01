package edu.school21.printer.logic;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

public class ImageToChar {
    public static void printCharArray(URL file, String white, String black) {
        try {
            BufferedImage buffer = ImageIO.read(file);
            ColoredPrinter cpWhite = new ColoredPrinter.Builder(0, false)
                    .background(Ansi.BColor.valueOf(white)).build();
            ColoredPrinter cpBlack = new ColoredPrinter.Builder(0, false)
                    .background(Ansi.BColor.valueOf(black)).build();

            for (int y = 0; y < buffer.getHeight(); y++) {
                for (int x = 0; x < buffer.getWidth(); x++) {
                    int color = buffer.getRGB(x, y);

                    if (color == Color.BLACK.getRGB()) {
                        cpBlack.print("  ");
                    } else {
                        cpWhite.print("  ");
                    }
                }
                System.out.println("");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
