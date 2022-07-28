package school21.spring.service.services;

import org.springframework.stereotype.Component;
import school21.spring.service.repositories.UsersRepository;

public interface UsersService {
    public String signUp(String email);
}
