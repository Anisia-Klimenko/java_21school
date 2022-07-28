package school21.spring.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

import java.util.Random;

@Component
public class UsersServiceImpl implements UsersService {
    private UsersRepository repository;

    @Autowired
    public UsersServiceImpl(@Qualifier("repoJdbcTemplate") UsersRepository repository) {
        this.repository = repository;
    }

    @Override
    public String signUp(String email) {
        String password = generatePassword();
        repository.save(new User(null, email), password);

        return password;
    }

    private String generatePassword() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);

        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }

        return buffer.toString();
    }
}
