import java.util.ArrayList;

public class CountThread extends Thread implements Runnable{
    private int id;
    private int start;
    private int end;
    private ArrayList<Integer> arrayList;
    private static int sumOfThreads = 0;

    public CountThread(int id, int start, int end, ArrayList<Integer> arrayList) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.arrayList = arrayList;
    }

    public static synchronized void showSum(int id, int startElem, int endElem, int sum) {
        System.out.println("Thread " + id + ": from "
                + startElem + " to " + endElem + " sum is " + sum);
        sumOfThreads += sum;
    }

    public static int getSumOfThreads() {
        return sumOfThreads;
    }

    @Override
    public void run() {
        int sum = 0;

        for (int i = start; i <= end; i++) {
            sum += arrayList.get(i);
        }

        showSum(id, start, end, sum);
    }

}
