package edu.school21.sockets.repositories;

import edu.school21.sockets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Component("usersRepositoryImpl")
public class UsersRepositoryImpl implements UsersRepository{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UsersRepositoryImpl(@Qualifier("hikariDataSource") DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jdbcTemplate.query("select * from repo.userTable where id=?", new Object[]{id},
                (resSet, rowNumber) -> new User(resSet.getLong("id"), resSet.getString("userName"),
                        resSet.getString("password"))).stream().findAny();
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from repo.userTable", (resSet, rowNumber) -> new User(resSet.getLong("id"),
                resSet.getString("userName"), resSet.getString("password")));
    }

    @Override
    public void save(User entity) {
        jdbcTemplate.update("insert into repo.userTable (userName, password) values (?, ?)", entity.getUserName(), entity.getPassword());
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update("update repo.userTable set userName=?, password=? where id=?", entity.getUserName(),
                entity.getPassword(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("delete from repo.userTable where id=?", id);
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        return jdbcTemplate.query("select * from repo.userTable where userName=?", new Object[]{userName},
                (resSet, rowNumber) -> new User(resSet.getLong("id"),
                        resSet.getString("userName"), resSet.getString("password"))).stream().findAny();
    }
}
