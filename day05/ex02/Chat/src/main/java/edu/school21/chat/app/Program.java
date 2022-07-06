package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        ds.setUsername("postgres");
        ds.setPassword("postgres");

        MessagesRepository repository = new MessagesRepositoryJdbcImpl(ds);

        User user = new User(1L, "Mark", "123123", new ArrayList(), new ArrayList());
        Chatroom room = new Chatroom(1L, "chat1", user, new ArrayList());
        Message message = new Message(10L, user, room, "Test text", LocalDateTime.now());

        repository.save(message);
        System.out.print("Success!\nNew message id=");
        System.out.println(message.getId());
    }
}
