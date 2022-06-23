package ex00;

public class Program {
    public static void main(String[] args) {
        int count = 20;

        if (args[0].length() != 0) {
            System.err.println("Wrong number of arguments");
            System.exit(-1);
        }

        if (!args[0].startsWith("--count=")) {
            System.err.println("Wrong parameter, should be: --count=_");
            System.exit(-1);
        }

        count = Integer.parseInt(args[0].substring(8));

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
