package ex02;

public class UsersArrayList implements UsersList{
    User[] users = new User[10];
    private Integer currLength = 0;

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
            currLength++;
        }
    }

    @Override
    public User getUserById(Integer id) {
        for (int i = 0; i < currLength; i++) {
            if (users[i].getId().equals(id)) {
                return users[i];
            }
        }

        throw new UserNotFoundException("User " + id + " not found");
    }

    @Override
    public User getUserByIndex(Integer index) {
        if (index >= 0 && index < currLength) {
            return users[index];
        }

        throw new UserNotFoundException("User number " + index + " not found");
    }

    @Override
    public Integer countUsers() {
        return currLength;
    }
}
