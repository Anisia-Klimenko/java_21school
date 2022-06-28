package ex03;

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

    public void setUrls(Queue<String> urls) {
        this.urls = urls;
    }

    public synchronized String getURL() {
        return urls.poll();
    }

    public synchronized void printMessage(String message) {
        System.out.println(message);
    }

    public void downloadFile(String line) {
        try {
            String[] split = line.split("\\s+");
            Integer number = Integer.parseInt(split[0]);
            InputStream url = new URL(split[1]).openStream();
            Path file = Paths.get(number.toString());

            if (Files.exists(file)) {
                printMessage("File number " + number + " won't be downloaded");
                return;
            }

            printMessage(Thread.currentThread().getName() + " start download file number " + number);
            Files.copy(url, file, StandardCopyOption.REPLACE_EXISTING);
            printMessage(Thread.currentThread().getName() + " finish download file number " + number);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        downloadFile(getURL());
    }
}
