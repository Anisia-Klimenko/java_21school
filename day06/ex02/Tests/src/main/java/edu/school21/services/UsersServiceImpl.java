package edu.school21.services;

import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;

public class UsersServiceImpl implements UsersRepository {
    @Override
    public User findByLogin(String login) {
        return null;
    }

    @Override
    public void update(User user) {

    }

    public boolean authenticate(String login, String password) {
        return false;
    }
}