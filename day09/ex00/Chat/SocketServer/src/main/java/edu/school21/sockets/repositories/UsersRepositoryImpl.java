package edu.school21.sockets.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import edu.school21.sockets.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Component("repoJdbcTemplate")
public class UsersRepositoryImpl implements UsersRepository{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UsersRepositoryImpl(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jdbcTemplate.query("select * from userTable where id=?", new Object[]{id},
                (resSet, rowNumber) -> new User(resSet.getLong("id"),
                        resSet.getString("email"), resSet.getString("password"))).stream().findAny();
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from userTable", (resSet, rowNumber) -> new User(resSet.getLong("id"),
                resSet.getString("email"), resSet.getString("password")));
    }

    @Override
    public void save(User entity) {
        jdbcTemplate.update("insert into userTable (email, password) values (?, ?)", entity.getEmail(), entity.getPassword());
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update("update userTable set email=?, password=? where id=?", entity.getEmail(), entity.getPassword(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("delete from userTable where id=?", id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jdbcTemplate.query("select * from userTable where email=?", new Object[]{email},
                (resSet, rowNumber) -> new User(resSet.getLong("id"),
                        resSet.getString("email"), resSet.getString("password"))).stream().findAny();
    }
}
