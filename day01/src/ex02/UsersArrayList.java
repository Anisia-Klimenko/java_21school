package ex02;

public class UsersArrayList implements UsersList{
    User[] users = new User[10];
    private int currLength = 0;

    @Override
    public void addUser(User newUser) {
        if (currLength < users.length) {
            users[currLength] = newUser;
            currLength++;
        } else {
            User[] newUsers = new User[(int) (users.length * 1.5)];

            for (int i = 0; i < users.length; i++) {
                newUsers[i] = users[i];
            }

            newUsers[users.length] = newUser;
            users = newUsers;
        }
    }

    @Override
    public User getUserById(int id) {
        for (int i = 0; i < currLength; i++) {
            if (users[i].getId() == id) {
                return users[i];
            }
        }

        throw new UserNotFoundException("User " + id + " not found");
    }

    @Override
    public User getUserByIndex(int index) {
        if (index >= 0 && index < currLength) {
            return users[index];
        }

        throw new UserNotFoundException("User number " + index + " not found");
    }

    @Override
    public int countUsers() {
        int count = 0;

        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) {
                count++;
            }
        }

        return count;
    }
}
