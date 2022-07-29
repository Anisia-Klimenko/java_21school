package school21.spring.service.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");

        System.out.println("--- JDBC FIND ALL---");
        UsersRepositoryJdbcImpl jdbc = applicationContext.getBean("jdbc", UsersRepositoryJdbcImpl.class);
        System.out.println(jdbc.findAll().toString());
        System.out.println(" ");

        System.out.println("--- JDBC Template FIND ALL---");
        UsersRepositoryJdbcTemplateImpl jdbcTemplate = applicationContext.getBean("jdbcTemplate", UsersRepositoryJdbcTemplateImpl.class);
        System.out.println(jdbcTemplate.findAll().toString());
    }
}
