package school21.spring.service.repositories;

import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    private DataSource ds;

    public UsersRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Optional<User> findById(Long id) {
        Optional<User> optionalUser;

        try (Connection connection = ds.getConnection();) {
            String query = "select * from repo.user where id=" + id;
            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(query);

            if (!result.next())
                throw new RuntimeException("User not found");

            optionalUser = Optional.of(new User(
                    result.getLong("id"),
                    result.getString("email")));

            statement.close();

            return optionalUser;
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();

        try (Connection connection = ds.getConnection();) {
            String query = "select * from repo.user";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                list.add(new User(
                        result.getLong("id"),
                        result.getString("email")));
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void save(User entity) {
        String query = "insert into repo.user (email) values (?)";

        try (Connection conn = ds.getConnection();) {
            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, entity.getEmail());
            statement.execute();

            ResultSet id = statement.getGeneratedKeys();
            id.next();
            entity.setId(id.getLong(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User entity) {
        String query = "update repo.user set email=? where id=?";

        try (Connection conn = ds.getConnection();) {
            PreparedStatement statement = conn.prepareStatement(query);

            statement.setString(1, entity.getEmail());
            statement.setDouble(2, entity.getId());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String query = "delete from repo.user where id=?";

        try (Connection conn = ds.getConnection();) {
            PreparedStatement statement = conn.prepareStatement(query);

            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<User> optionalUser;

        try (Connection connection = ds.getConnection();) {
            String query = "select * from repo.user where email='" + email + "'";
            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(query);

            if (!result.next())
                throw new RuntimeException("User not found");

            optionalUser = Optional.of(new User(
                    result.getLong("id"),
                    result.getString("email")));

            statement.close();

            return optionalUser;
        } catch (SQLException e) {
            return Optional.empty();
        }
    }
}
