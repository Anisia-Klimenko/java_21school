package ex03;

public class UserIdsGenerator {
    private static int id = 0;
    private static final UserIdsGenerator UIGen = new UserIdsGenerator();

    public static UserIdsGenerator getInstance() {
        return UIGen;
    }

    public static int generateId() {
        return id++;
    }
}
