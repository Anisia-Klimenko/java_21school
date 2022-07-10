package app;

import javafx.scene.effect.Reflection;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Program {
    public static void main(String[] args) {
        Reflections reflections = new Reflections("classes", new SubTypesScanner(false));
        Scanner scanner = new Scanner(System.in);

        System.out.println("Classes:");
        Set<Class> allClasses = new HashSet<>(reflections.getSubTypesOf(Object.class));

        for (Class c : allClasses) {
            System.out.println("\t-\t" + c.getSimpleName());
        }

        System.out.println("---------------------");
        System.out.print("Enter class name:\n -> ");

        String input = scanner.nextLine();
        Class currentClass = null;
        try {
            currentClass = Class.forName("classes." + input);
        } catch (ClassNotFoundException e) {
            System.err.println("Class " + input + " not found");
            System.exit(1);
        }

        System.out.println("---------------------\nfields:");
        Field[] fields = currentClass.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("\t" + field.getType().getSimpleName() + " " + field.getName());
        }

        System.out.println("methods:");
        Method[] methods = currentClass.getDeclaredMethods();
        for (Method method : methods) {
            String output = Arrays.toString(method.getParameterTypes());
            System.out.println("\t" + method.getReturnType().getSimpleName() + " " + method.getName()
                    + "(" + output.substring(1, output.length() - 1) + ")");
        }

        System.out.println("---------------------\nLet's create an object.");
        Constructor<?> constructor = currentClass.getConstructors()[0].getParameterCount() > 0 ?
                currentClass.getConstructors()[0] : currentClass.getConstructors()[1];
        for (Parameter parameter : constructor.getParameters()) {
            System.out.println(parameter);
        }
        System.out.println(constructor.getParameterCount());

    }
}
