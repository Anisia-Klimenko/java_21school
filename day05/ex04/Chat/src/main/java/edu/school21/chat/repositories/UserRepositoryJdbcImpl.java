package edu.school21.chat.repositories;

import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryJdbcImpl implements UserRepository{
    private DataSource ds;

    public UserRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public List<User> findAll(int page, int size) {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM chat.user";

        try (Connection conn = ds.getConnection();
             PreparedStatement statement = conn.prepareStatement(query);) {
            statement.execute();

            ResultSet result = statement.getResultSet();

            while (result.next()) {
                userList.add(new User(result.getLong("id"), result.getString("login"),
                        result.getString("password"), null, null));
            }
        } catch (SQLException e) {
            throw new NotSavedSubEntityException("Can't save message");
        }
        return userList;
    }
}
