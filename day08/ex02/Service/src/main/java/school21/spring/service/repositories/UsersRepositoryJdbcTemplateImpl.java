package school21.spring.service.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Component("repoJdbcTemplate")
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UsersRepositoryJdbcTemplateImpl(@Qualifier("hikariDataSource") DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jdbcTemplate.query("select * from userTable where id=?", new Object[]{id},
                (resSet, rowNumber) -> new User(resSet.getLong(1),
                        resSet.getString(2))).stream().findAny();
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from userTable", (resSet, rowNumber) -> new User(resSet.getLong("id"),
                resSet.getString("email")));
    }

    @Override
    public void save(User entity, String password) {
        jdbcTemplate.update("insert into userTable (email, password) values (?, ?)", entity.getEmail(), password);
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update("update userTable set email=? where id=?", entity.getEmail(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("delete from userTable where id=?", id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jdbcTemplate.query("select * from userTable where email=?", new Object[]{email},
                (resSet, rowNumber) -> new User(resSet.getLong("id"),
                resSet.getString("email"))).stream().findAny();
    }
}
