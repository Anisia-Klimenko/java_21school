package school21.spring.service.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");

        System.out.println("--- JDBC ---");
        UsersRepositoryJdbcImpl jdbc = applicationContext.getBean("jdbc", UsersRepositoryJdbcImpl.class);
        System.out.println("FIND BY ID\t\t" + jdbc.findById(1L));
        System.out.println("FIND ALL\t\t" + jdbc.findAll().toString());
        System.out.println("FIND BY EMAIL\t" + jdbc.findByEmail("sam@mail.com"));
        System.out.println(" ");

        System.out.println("--- JDBC Template ---");
        UsersRepositoryJdbcTemplateImpl jdbcTemplate = applicationContext.getBean("jdbcTemplate", UsersRepositoryJdbcTemplateImpl.class);
        System.out.println("FIND BY ID\t\t" + jdbcTemplate.findById(1L));
        System.out.println("FIND ALL\t\t" + jdbcTemplate.findAll().toString());
        System.out.println("FIND BY EMAIL\t" + jdbcTemplate.findByEmail("sam@mail.com"));
    }
}
