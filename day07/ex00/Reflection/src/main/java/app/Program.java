package app;

public class Program {
    public static void main(String[] args) {
        ReflectionClass reflection = new ReflectionClass();

        reflection.showClasses();
        System.out.println("---------------------");
        reflection.showClassInfo();
        System.out.println("---------------------");
        reflection.createObject();
        System.out.println("---------------------");
        reflection.changeField();
        System.out.println("---------------------");
        reflection.callMethod();
    }
}
