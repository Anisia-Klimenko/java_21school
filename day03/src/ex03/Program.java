package ex03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Program {
    public static void main(String[] args) {
//        if (args.length != 1) {
//            System.err.println("Should be an argument: --threadsCount=_");
//            System.exit(-1);
//        }
//
//        int threadsCount = 0;
//
//        if (args[0].startsWith("--threadsCount")) {
//            threadsCount = Integer.parseInt(args[0].substring(14));
//        }

        int threadsCount = 3;
        Queue<String> urls = new LinkedList<>();

        try {
            BufferedReader buffer = new BufferedReader(new FileReader("ex03/files_urls.txt"));
            String line;

            while ((line = buffer.readLine()) != null) {
                if (!line.isEmpty()) {
                    urls.add(line);
                }
            }

            buffer.close();

            DownloadThread thread = new DownloadThread();
            thread.setUrls(urls);

            for (int i = 0; i < threadsCount; i++) {
                thread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
