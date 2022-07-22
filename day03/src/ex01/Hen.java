public class Hen extends Thread implements Runnable{
    private int count;

    public Hen(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            Program.sayHen();
        }
    }
}
