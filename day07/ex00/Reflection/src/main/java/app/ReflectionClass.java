package app;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ReflectionClass {
    private Reflections reflections;
    private Class currentClass;
    private Object object;
    private Scanner scanner = new Scanner(System.in);
    Set<Class> allClasses;

    public ReflectionClass() {
        this.reflections = new Reflections("classes", new SubTypesScanner(false));
        this.object = null;
        this.currentClass = null;
    }

    public void showClasses() {
        System.out.println("Classes:");

        allClasses = new HashSet<>(reflections.getSubTypesOf(Object.class));

        for (Class c : allClasses) {
            System.out.println("\t-\t" + c.getSimpleName());
        }
    }

    public void showClassInfo() {
        System.out.print("Enter class name:\n -> ");

        String input = scanner.nextLine();
        currentClass = allClasses.stream().filter(c -> c.getSimpleName().equals(input)).findFirst().orElse(null);

        if (currentClass == null) {
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
    }

    public void createObject() {
        System.out.println("Let's create an object.");

        for (Constructor c : currentClass.getConstructors()) {
            if (c.getParameterCount() == currentClass.getDeclaredFields().length) {
                Field[] fields = currentClass.getDeclaredFields();

                try {
                    object = currentClass.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }

                for (Field f: fields) {
                    System.out.print(f.getName() + ":\n -> ");

                    f.setAccessible(true);

                    try {
                        Object value = getValue(f.getType().getSimpleName(), scanner.nextLine());

                        if (value != null) {
                            f.set(object, value);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (NumberFormatException e) {
                        System.err.println("Wrong input type!");
                        System.exit(1);
                    }
                }

                if (object != null) {
                    System.out.println("Object created: " + object.toString());
                }
            }
        }
    }

    private Object getValue(String type, String object) {
        switch (type.toLowerCase()) {
            case "string":
                return object;
            case "int":
            case "integer":
                return Integer.parseInt(object);
            case "double":
                return Double.parseDouble(object);
            case "float":
                return Float.parseFloat(object);
            case "long":
                return Long.parseLong(object);
            case "short":
                return Short.parseShort(object);
            case "boolean":
                return Boolean.parseBoolean(object);
            case "byte":
                return Byte.parseByte(object);
            case "char":
                return object.charAt(0);
            default:
                return null;
        }
    }

    public void changeField() {
        System.out.print("Enter name of the field for changing:\n -> ");

        Field[] fields = currentClass.getDeclaredFields();
        String line = scanner.nextLine();
        Field field = Arrays.stream(fields).filter(f ->
                f.getName().equals(line)).findFirst().orElse(null);

        if (field == null) {
            System.err.println("Field not found!");
            System.exit(1);
        }

        System.out.print("Enter " + field.getType().getSimpleName() + " value:\n -> ");

        Object value = null;

        try {
            value = getValue(field.getType().getSimpleName(), scanner.nextLine());
        } catch (NumberFormatException e) {
            System.err.println("Wrong input type!");
            System.exit(1);
        }

        if (value != null) {
            try {
                field.setAccessible(true);
                field.set(object, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        if (object != null) {
            System.out.println("Object updated: " + object.toString());
        }
    }

    public void callMethod() {
        System.out.print("Enter name of the method for call:\n -> ");

        String line = scanner.nextLine();
        Method[] methods = currentClass.getDeclaredMethods();
        Method method = Arrays.stream(methods).filter(m ->
                m.getName().equals(line.split("\\(")[0])).findFirst().orElse(null);

        if (method == null) {
            System.err.println("Method not found!");
            return;
        }

        Parameter[] parameters = method.getParameters();
        Object[] args = new Object[parameters.length];
        Object result = null;

        for (int i = 0; i < parameters.length; i++) {
            System.out.print("Enter " + parameters[i].getType().getSimpleName() + " value:\n -> ");
            args[i] = getValue(parameters[i].getType().getSimpleName(), scanner.nextLine());
        }

        method.setAccessible(true);

        try {
            result = method.invoke(object, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        if (!method.getReturnType().getSimpleName().equals("void")) {
            System.out.println("Method returned:");
            System.out.println(result);
        }
    }
}
