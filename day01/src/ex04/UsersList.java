package ex04;

public interface UsersList {
    public void addUser(User newUser);
    public User getUserById(int id) throws UserNotFoundException;
    public User getUserByIndex (int index) throws UserNotFoundException;
    public int countUsers();
}
