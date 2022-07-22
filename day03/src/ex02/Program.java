import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.Math.random;

public class Program {
    public static String getArgs(String string) {
        if (!string.startsWith("--arraySize=") && !string.startsWith("--threadsCount=")) {
            return null;
        }

        String[] split = string.split("=");

        return split[1];
    }

    public static int findSum(ArrayList<Integer> array) {
        int sum = 0;

        for (Integer integer : array) {
            sum += integer;
        }
        return sum;
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Should be 2 arguments: --arraySize=_ --threadsCount=_");
            System.exit(-1);
        }

        int arraySize = 0;
        int threadsCount = 0;

        try {
            arraySize = Integer.parseInt(Objects.requireNonNull(getArgs(args[0].startsWith("--arraySize=") ? args[0] : args[1])));
            threadsCount = Integer.parseInt(Objects.requireNonNull(getArgs(args[0].startsWith("--arraySize=") ? args[1] : args[0])));
        } catch (NullPointerException e) {
            System.err.println("Wrong arguments");
            System.exit(-1);
        }

        if (arraySize > 2000000) {
            arraySize = 2000000;
        }

        if (threadsCount > arraySize) {
            threadsCount = arraySize;
        }

        ArrayList<Integer> array = new ArrayList<Integer>(arraySize);

        for (int i = 0; i < arraySize; i++) {
            int j = (int) (random() * 2000) - 1000;
            array.add(j);
        }

        System.out.println("Sum: " + findSum(array));

        int sizeOfThread = arraySize / threadsCount;

        List<Thread> threadList = new ArrayList<>(threadsCount);

        for (int i = 0; i < threadsCount; i++) {
            if (i + 1 == threadsCount) {
                threadList.add(new CountThread(i + 1, i * sizeOfThread, arraySize - 1, array));
            } else {
                threadList.add(new CountThread(i + 1, i * sizeOfThread, (i + 1) * sizeOfThread - 1, array));
            }
        }

        for (Thread tr : threadList) {
            tr.start();
        }

        for (Thread tr : threadList) {
            try {
                tr.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Sum by threads: " + CountThread.getSumOfThreads());
    }
}
