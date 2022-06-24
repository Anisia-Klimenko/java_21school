package ex02;

import java.util.ArrayList;

public class CountThread extends Thread implements Runnable{
    private int start;
    private int end;
    private ArrayList<Integer> arrayList;
    private static int sumOfThreads = 0;

    public CountThread(int start, int end, ArrayList<Integer> arrayList) {
        this.start = start;
        this.end = end;
        this.arrayList = arrayList;
    }

    public static synchronized void showSum(int startElem, int endElem, int sum) {
        System.out.println("Thread " + Thread.currentThread().getName() + ": from "
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

        showSum(arrayList.get(start), arrayList.get(end), sum);
    }

}
