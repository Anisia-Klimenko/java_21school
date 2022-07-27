package school21.spring.service.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository{
    private DataSource ds;
    private JdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(DataSource ds) {
        this.ds = ds;
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jdbcTemplate.query("select * from repo.user where id=?", new Object[]{id},
                (resSet, rowNumber) -> new User(resSet.getLong(1),
                        resSet.getString(2))).stream().findAny();
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from repo.user", (resSet, rowNumber) -> new User(resSet.getLong("id"),
                resSet.getString("email")));
    }

    @Override
    public void save(User entity) {
        jdbcTemplate.update("insert into repo.user (email) values (?)", entity.getEmail());
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update("update repo.user set email=? where id=?", entity.getEmail(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("delete from repo.user where id=?", id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jdbcTemplate.query("select * from repo.user where email=?", new Object[]{email},
                (resSet, rowNumber) -> new User(resSet.getLong("id"),
                resSet.getString("email"))).stream().findAny();
    }
}
