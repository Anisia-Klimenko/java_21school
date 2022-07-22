package edu.school21.printer.app;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import edu.school21.printer.logic.ImageToChar;

@Parameters(separators = "=")
public class Program {
    @Parameter(names={"--white", "-w"})
    String white;
    @Parameter(names={"--black", "-b"})
    String black;

    public static void main(String[] args) {
        Program program = new Program();

        JCommander.newBuilder()
                .addObject(program)
                .build()
                .parse(args);
        program.run();
    }

    public void run() {
        ImageToChar.printCharArray(Program.class.getResource("/resources/image.bmp"),
                white.toUpperCase(), black.toUpperCase());
    }
}
