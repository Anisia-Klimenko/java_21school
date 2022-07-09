package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.UserRepository;
import edu.school21.chat.repositories.UserRepositoryJdbcImpl;

import java.util.List;

public class Program {
    public static void main(String[] args) {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        ds.setUsername("postgres");
        ds.setPassword("postgres");

        UserRepository repository = new UserRepositoryJdbcImpl(ds);
        List<User> users = repository.findAll(0, 3);

        for (User u : users) {
            System.out.println(u.toString());
        }
    }
}
