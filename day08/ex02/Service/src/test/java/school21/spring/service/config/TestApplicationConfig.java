package school21.spring.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.stereotype.Component;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;
import school21.spring.service.services.UsersServiceImpl;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Configuration
//@ComponentScan(basePackages = "school21.spring.service")
public class TestApplicationConfig {
    private DataSource ds;
    private JdbcTemplate jdbcTemplate;

    @Bean
    public DataSource createTest() {
        ds = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .build();
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcTemplate.execute("drop table if exists userTable");
        jdbcTemplate.execute("create table userTable (\n" +
                "    id      bigint primary key identity,\n" +
                "    email       varchar(50) ,\n" +
                "    password    varchar(50)\n" +
                ")");

        return ds;
    }

    @Bean
    public UsersServiceImpl usersService() {
        return new UsersServiceImpl(usersRepositoryJdbcTemplate());
    }

    @Bean
    public UsersRepositoryJdbcImpl usersRepositoryJdbc() {
        return new UsersRepositoryJdbcImpl(createTest());
    }

    @Bean
    public UsersRepositoryJdbcTemplateImpl usersRepositoryJdbcTemplate() {
        return new UsersRepositoryJdbcTemplateImpl(createTest());
    }
}
