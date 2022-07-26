import java.io.*;
import java.util.ArrayList;
import java.util.TreeSet;

public class Program {
    public static TreeSet<String> DICTIONARY = new TreeSet<>();

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Should be 2 argument: file1 file2");
            System.exit(-1);
        }

        String file1 = args[0];
        String file2 = args[1];

        try {
            BufferedReader buffer = new BufferedReader(new FileReader(file1));
            ArrayList<String> words1 = readFiles(buffer);
            buffer.close();

            buffer = new BufferedReader(new FileReader(file2));
            ArrayList<String> words2 = readFiles(buffer);
            buffer.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter("dictionary.txt"));

            for (String w : DICTIONARY) {
                writer.write(w + "\n");
            }

            writer.close();

            int[] frequencyArray1 = findFrequency(words1);
            int[] frequencyArray2 = findFrequency(words2);

            String similarity = String.format("%.3f", countSimilarity(frequencyArray1, frequencyArray2));

            System.out.println("Similarity = " + similarity.substring(0, similarity.length() - 1));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static double countSimilarity (int[] array1, int[] array2) {
        int numerator = 0;
        double denominator = 1;
        double norm1 = 0;
        double norm2 = 0;

        for (int i = 0; i < array1.length; i++) {
            numerator += array1[i] * array2[i];
        }

        for (int i = 0; i < array1.length; i++) {
            norm1 += array1[i] * array1[i];
            norm2 += array2[i] * array2[i];
        }

        if (norm1 != 0 && norm2 != 0) {
            denominator = Math.sqrt(norm1) * Math.sqrt(norm2);
        }

        return numerator / denominator;
    }

    public static int[] findFrequency(ArrayList<String> words) {
        int[] frequencyArray = new int[DICTIONARY.size()];
        int index = 0;
        int count;

        for (String elem : DICTIONARY) {
            count = 0;

            for (String w : words) {
                if (elem.equals(w)) {
                    count++;
                }
            }

            frequencyArray[index] = count;
            index++;
        }

        return frequencyArray;
    }

    public static ArrayList<String> readFiles(BufferedReader buffer) throws IOException {
        String line;
        String[] split;
        ArrayList<String> words = new ArrayList<>();

        while ((line = buffer.readLine()) != null) {
            if (!line.isEmpty()) {
                split = line.toString().split("\\s+");

                for (int i = 0; i < split.length; i++) {
                    if (!split[i].isEmpty()) {
                        DICTIONARY.add(split[i]);
                        words.add(split[i]);
                    }
                }
            }
        }

        return words;
    }
}
