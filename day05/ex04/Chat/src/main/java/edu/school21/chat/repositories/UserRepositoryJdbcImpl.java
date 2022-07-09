package edu.school21.chat.repositories;

import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepositoryJdbcImpl implements UserRepository{
    private DataSource ds;

    public UserRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public List<User> findAll(int page, int size) {
        List<User> userList = new ArrayList<>();
        String query = "with created as (select owner,\n" +
                "                        id  as created_id,\n" +
                "                        name\n" +
                "                from chat.chatroom\n" +
                "                order by owner),\n" +
                "     active as (select distinct author,\n" +
                "                                chatroom as active_id,\n" +
                "                                name as active_name\n" +
                "                from chat.message\n" +
                "                    join chat.chatroom\n" +
                "                        on message.chatroom=chatroom.id\n" +
                "                order by author)\n" +
                "select distinct chat.user.id as user_id,\n" +
                "                chat.user.login as login,\n" +
                "                chat.user.password as password,\n" +
                "                created_id,\n" +
                "                created.name as created_name,\n" +
                "                active_id,\n" +
                "                active_name\n" +
                "from chat.user\n" +
                "    left join created\n" +
                "        on chat.user.id = created.owner\n" +
                "    left join active\n" +
                "        on chat.user.id = active.author\n" +
                "order by user_id;";

        try (Connection conn = ds.getConnection();
             PreparedStatement statement = conn.prepareStatement(query);) {
            statement.execute();


            ResultSet result = statement.getResultSet();
            HashSet<Chatroom> createdRooms = new HashSet<>();
            HashSet<Chatroom> activeRooms = new HashSet<>();
            Long currentUserId = 0L;
            boolean ifNextUser;
            User newUser = null;

            while (result.next()) {
                ifNextUser = currentUserId != result.getLong("user_id");

                if (ifNextUser && currentUserId != 0L) {
                    newUser.setCreatedRooms(createdRooms.size() > 0 ? new ArrayList<>(createdRooms) : null);
                    newUser.setChatRooms(activeRooms.size() > 0 ? new ArrayList<>(activeRooms) : null);
                    userList.add(newUser);
                }

                currentUserId = result.getLong("user_id");

                if (ifNextUser) {
                    newUser = new User(currentUserId, result.getString("login"), result.getString("password"), null, null);
                    createdRooms.clear();
                    activeRooms.clear();
                }

                if (result.getString("created_name") != null) {
                    createdRooms.add(new Chatroom(
                            result.getLong("created_id"),
                            result.getString("created_name"),
                            new User(result.getLong("user_id"),
                                    result.getString("login"),
                                    result.getString("password"),
                                    null,
                                    null),
                            null));
                }

                if (result.getString("active_name") != null) {
                    activeRooms.add(new Chatroom(
                            result.getLong("active_id"),
                            result.getString("active_name"),
                            new User(result.getLong("user_id"),
                                    result.getString("login"),
                                    result.getString("password"),
                                    null,
                                    null),
                            null));
                }
            }
            newUser.setCreatedRooms(createdRooms.size() > 0 ? new ArrayList<>(createdRooms) : null);
            newUser.setChatRooms(activeRooms.size() > 0 ? new ArrayList<>(activeRooms) : null);
            userList.add(newUser);
        } catch (SQLException e) {
            throw new NotSavedSubEntityException("Can't get users");
        }

        return userList.subList(Math.min(page * size, userList.size()), Math.min((page + 1) * size, userList.size()));
    }
}
