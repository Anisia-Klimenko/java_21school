package ex02;

public class Program {
    public static void main(String[] args) {
        UsersArrayList users = new UsersArrayList();

        User Mike = new User();
        User John = new User();

        Mike.setName("Mike");
        Mike.setBalance(1000);

        John.setName("John");
        John.setBalance(2000);

        users.addUser(Mike);

        System.out.println(users.getUserByIndex(0).getName());

        users.addUser(John);

        System.out.println(users.getUserById(1).getName());

        for (int i = 0; i < 15; i++) {
            users.addUser(Mike);
        }

        System.out.println(users.countUsers());

        System.out.println(users.getUserById(3).getName());
    }
}
