import javax.sound.sampled.Port;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Queue;

public class DownloadThread extends Thread implements Runnable{
    private Queue<String> urls;

    public synchronized void printMessage(String message) {
        System.out.println(message);
    }

    public void downloadFile(String line) {
        try {
            String[] split = line.split("\\s+");
            int number = Integer.parseInt(split[0]);
            Path file = Paths.get(split[1]);
            InputStream url = new URL(split[1]).openStream();


            if (Files.exists(file)) {
                printMessage("File number " + number + " won't be downloaded");
                return;
            }

            printMessage(Thread.currentThread().getName() + " start download file number " + number);
            Files.copy(url, file.getFileName(), StandardCopyOption.REPLACE_EXISTING);
            printMessage(Thread.currentThread().getName() + " finish download file number " + number);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        String url;
        while ((url = Program.getUrl()) != null){
            downloadFile(url);
        }
    }
}
