public class Program {
    public static boolean isPrinted = true;

    public static synchronized void sayHen() {
        if (isPrinted) {
            try {
                Program.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Hen");
        isPrinted = true;
        Program.class.notify();
    }

    public static synchronized void sayEgg() {
        if (!isPrinted) {
            try {
                Program.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Egg");
        isPrinted = false;
        Program.class.notify();
    }

    public static void main(String[] args) {
        int count = 0;

        if (args.length == 1 && args[0].startsWith("--count=")) {
            count = Integer.parseInt(args[0].substring(8));
        } else {
            System.out.println("Should be one parameter with number of threads: --count=_");
            System.exit(0);
        }

        Hen hen = new Hen(count);
        Egg egg = new Egg(count);

        hen.start();
        egg.start();
    }
}
