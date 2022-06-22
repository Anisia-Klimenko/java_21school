package ex04;

public interface UsersList {
    public void addUser(User newUser);
    public User getUserById(Integer id) throws UserNotFoundException;
    public User getUserByIndex (Integer index) throws UserNotFoundException;
    public Integer countUsers();
}
