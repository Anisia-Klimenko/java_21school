import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Program {
    public static Queue<String> urls = new LinkedList<>();

    public static synchronized String getUrl() {
        return urls.poll();
    }


    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Should be an argument: --threadsCount=_");
            System.exit(-1);
        }

        int threadsCount = 0;

        if (args[0].startsWith("--threadsCount")) {
            threadsCount = Integer.parseInt(args[0].substring(15));
        }

        try {
            BufferedReader buffer = new BufferedReader(new FileReader("files_urls.txt"));
            String line;

            while ((line = buffer.readLine()) != null) {
                if (!line.isEmpty()) {
                    urls.add(line);
                }
            }

            buffer.close();

            for (int i = 0; i < threadsCount; i++) {
                new DownloadThread().start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
