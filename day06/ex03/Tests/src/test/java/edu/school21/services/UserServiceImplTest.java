package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class UserServiceImplTest {
    @Mock
    UsersRepository usersRepository;

    UsersServiceImpl usersService;

    public UserServiceImplTest() {
        MockitoAnnotations.initMocks(this);
        usersRepository = Mockito.mock(UsersRepository.class);
        usersService = new UsersServiceImpl(usersRepository);
    }

    @Test
    void authCorrectLoginPasswordTest() {
        User userCorrect = new User(1L, "acristin", "acpasswd", false);
        Mockito.when(usersRepository.findByLogin("acristin")).thenReturn(userCorrect);
        Assertions.assertTrue(usersService.authenticate("acristin", "acpasswd"));
        Mockito.verify(usersRepository, Mockito.times(1)).update(Mockito.any());
    }

    @Test
    void authNotCorrectLogin() {
        Mockito.when(usersRepository.findByLogin("wrongLogin")).thenThrow(EntityNotFoundException.class);
        Assertions.assertThrowsExactly(EntityNotFoundException.class,
                                        () -> usersService.authenticate("wrongLogin", "qwerty"));
    }

    @Test
    void authNotCorrectPassword() {
        User userNotCorrectPasswd = new User(1L, "acristin", "acpasswd", false);
        Mockito.when(usersRepository.findByLogin("acristin")).thenReturn(userNotCorrectPasswd);
        Assertions.assertFalse(usersService.authenticate("acristin", "wrongPassword"));
    }

    @Test
    void authAlreadyAuth() {
        User userAlreadyAuth = new User(1L, "acristin", "acpasswd", true);
        Mockito.when(usersRepository.findByLogin("acristin")).thenReturn(userAlreadyAuth);
        Assertions.assertThrowsExactly(AlreadyAuthenticatedException.class,
                () -> usersService.authenticate("acristin", "acpasswd"));
    }
}
