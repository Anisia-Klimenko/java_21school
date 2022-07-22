package ex02;

public class Program {
    public static void main(String[] args) {
        UsersArrayList users = new UsersArrayList();

        User Mike = new User("Mike", 1000);
        User John = new User("John", 2000);

        users.addUser(Mike);
        System.out.println("Add user Mike and get user name by index 0: " + users.getUserByIndex(0).getName());
        users.addUser(John);
        System.out.println("Add user John and get user name by id 1: " + users.getUserById(1).getName());

        for (int i = 0; i < 15; i++) {
            users.addUser(Mike);
        }

        System.out.println("Add 15 more users, count users: " + users.countUsers());
        System.out.println("Get user by id 3 (UserNotFoundException): " + users.getUserById(3).getName());
    }
}
