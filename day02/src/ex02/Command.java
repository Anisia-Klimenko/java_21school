package ex02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Command {
    private Path currentDir;

    public Command(Path currentDir) {
        this.currentDir = currentDir;
    }

    public void ls() {
        try (Stream<Path> streamSubPaths = Files.walk(currentDir, 1)) {
            List<Path> listSubPaths = streamSubPaths.collect(Collectors.toList());
            for (Path path : listSubPaths) {
                if (!path.getFileName().toString().startsWith(".") && !path.equals(currentDir)) {
                    System.out.println(path.getFileName() + " " + (Files.size(path) / 1024) + "KB");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mv() {

    }
}
