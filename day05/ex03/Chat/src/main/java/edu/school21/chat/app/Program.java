package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public class Program {
    public static void main(String[] args) {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        ds.setUsername("postgres");
        ds.setPassword("postgres");

        MessagesRepository repository = new MessagesRepositoryJdbcImpl(ds);

        Optional<Message> messageOptional = null;
        try {
            messageOptional = repository.findById(4L);

            if (messageOptional.isPresent()) {
                Message message = messageOptional.get();
                User user = new User(1L, "Mark", "123123", new ArrayList(), new ArrayList());

                message.setText(null);
                message.setAuthor(user);
                message.setDate(LocalDateTime.now());

                repository.update(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
