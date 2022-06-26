package ex02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Command {
    private Path currentDir;

    public Command(Path currentDir) {
        this.currentDir = currentDir;
    }

    public Path getCurrentDir() {
        return currentDir;
    }

    public void setCurrentDir(Path currentDir) {
        this.currentDir = currentDir;
    }

    public void ls() throws RuntimeException {
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

    public void mv(String src, String dest) {
        File srcFile = new File(src);
        File destFile = new File(dest);

        try {
            if (destFile.isDirectory()) {
                destFile = new File(dest + "/" + srcFile.getName());
                Files.move(srcFile.toPath(), destFile.toPath(), REPLACE_EXISTING);
            } else {
                Files.move(srcFile.toPath(), destFile.toPath(), REPLACE_EXISTING);
            }
        } catch (IOException e) {
            System.out.println("No such file " + srcFile.toString());
        }
    }

    public void cd(Path path) {
        Path absolutePath = Paths.get(getCurrentDir().toString() + "/" + path.toString());

        if (Files.exists(absolutePath) && Files.isDirectory(absolutePath)) {
            setCurrentDir(absolutePath.normalize());
            System.out.println(getCurrentDir().toString());
        } else {
            System.out.println("No such directory");
        }
    }
}
