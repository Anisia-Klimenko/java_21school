package school21.spring.service.services;

import org.springframework.stereotype.Component;
import school21.spring.service.models.User;

import java.util.List;
import java.util.Optional;

@Component("userServiceBean")
public class UsersServiceImpl implements UsersService{
    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void save(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public String signUp(String email) {
        return null;
    }
}
