package ex00;

import java.io.*;
import java.util.*;

public class Program {
    public static final String PROCESSED = "PROCESSED";
    public static final String UNDEFINED = "UNDEFINED";

    public static void main(String[] args) {
        Map<String, String> signatures = new HashMap<>();

        try {
            FileInputStream inputStream = new FileInputStream(new File("ex00/signatures.txt"));

            int i;
            StringBuilder buffer = new StringBuilder();
            
            while ((i = inputStream.read()) != -1) {
                if ((char) i == '\n') {
                    String[] split = buffer.toString().split(", ");
                    signatures.put(split[0], split[1]);
                    buffer.setLength(0);
                } else {
                    buffer.append((char) i);
                }
            }
            if (buffer.length() != 0) {
                String[] split = buffer.toString().split(", ");
                signatures.put(split[0], split[1]);
            }

            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder bytes = new StringBuilder();

        try {
            Scanner scanner = new Scanner(System.in);
            boolean isProcessed = false;
            FileOutputStream out = new FileOutputStream(new File("ex00/result.txt"));

            System.out.print(" -> ");

            String line = scanner.next();

            while (!line.equals("42")) {
                FileInputStream test = new FileInputStream(new File(line));

                for (int i = 0; i < 8; i++) {
                    bytes.append(Integer.toHexString(test.read()).toUpperCase()).append(" ");
                }

                for (Map.Entry<String, String> elem : signatures.entrySet()) {
                    if (bytes.toString().startsWith(elem.getValue())) {
                        for (int i = 0; i < elem.getKey().length(); i++) {
                            out.write((int) elem.getKey().charAt(i));
                        }

                        out.write((int) '\n');
                        System.out.println(PROCESSED);
                        isProcessed = true;
                    }
                }

                if (!isProcessed) {
                    System.out.println(UNDEFINED);
                }

                bytes.setLength(0);
                System.out.print(" -> ");
                line = scanner.next();
                test.close();
            }

            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
