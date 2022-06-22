package ex03;

public class UserIdsGenerator {
    private static Integer id = 0;
    private static final UserIdsGenerator UIGen = new UserIdsGenerator();

    public static UserIdsGenerator getInstance() {
        return UIGen;
    }

    public static Integer generateId() {
        return id++;
    }
}
