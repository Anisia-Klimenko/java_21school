package ex00;

public class Program {
    public static void main(String[] args) {
        int count = 0;

        if (args[0].length() == 1 && args[0].startsWith("--count=")) {
            count = Integer.parseInt(args[0].substring(8));
        } else {
            System.out.println("Should be one parameter with number of threads: --count=_");
            System.exit(0);
        }

        Egg egg = new Egg(count);
        Hen hen = new Hen(count);

        egg.start();
        hen.start();

        try {
            egg.join();
            hen.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < count; i++) {
            System.out.println("Human");
        }
    }
}
