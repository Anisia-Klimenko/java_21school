package edu.school21.sockets.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;

import java.util.Random;

@Component
public class UsersServiceImpl implements UsersService{
    private UsersRepository repository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsersServiceImpl(UsersRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String signUp(String userName, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        repository.save(new User(null, userName, encodedPassword));
        return encodedPassword;
    }
}
