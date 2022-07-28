package school21.spring.service.application;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import school21.spring.service.config.ApplicationConfig;
import school21.spring.service.services.UsersService;
import school21.spring.service.services.UsersServiceImpl;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        UsersService usersService = applicationContext.getBean("usersServiceImpl", UsersServiceImpl.class);

        System.out.println("Random password for newEmail@mail.com : " + usersService.signUp("newEmail@mail.com"));

        applicationContext.close();
    }
}