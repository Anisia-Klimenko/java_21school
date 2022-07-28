package school21.spring.service.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import school21.spring.service.config.TestApplicationConfig;

public class UsersServiceImplTest {
    @Test
    void signUpTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestApplicationConfig.class);
        UsersService usersService = context.getBean(UsersServiceImpl.class);
        String password = usersService.signUp("testEmail@mail.com");
        Assertions.assertNotEquals(password, "");
        context.close();
    }
}
