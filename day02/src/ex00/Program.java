package ex00;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Program {
//    public static final String PROCESSED = "PROCESSED";
//    public static final String UNDEFINED = "UNDEFINED";
//    public static final String SIGNATURES = "/Users/acristin/java_21school/day02/src/ex00/signatures.txt";

    public static void main(String[] args) {
        System.out.println("here");
//        Map<String, String> signatures = new HashMap<>();
//        List<Character> buffer = new ArrayList<Character>();
//
        try {
            System.out.println("here");
            FileInputStream inputStream = new FileInputStream(new File("ex00/signatures.txt"));
            System.out.println("here");

////            int i = 0;
////            String tmp = null;
////            while ((i = inputStream.read()) != -1) {
////                System.out.println("here");
////                if ((char) i == '\n') {
////                    String[] split = buffer.toString().split(",");
////                    signatures.put(split[0].trim(), split[1].trim());
////                    buffer.clear();
////                }
////                buffer.add(buffer.size(), (char) i);
////            }
////
////            for (String name: signatures.keySet()) {
////                String key = name.toString();
////                String value = signatures.get(name).toString();
////                System.out.println(key + " " + value);
//            }
        } catch (IOException e) {
            e.printStackTrace();
//            e.getMessage();
        }
    }
}
