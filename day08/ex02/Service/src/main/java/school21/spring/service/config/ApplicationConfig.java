package school21.spring.service.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("school21.spring.service")
@PropertySource("classpath:db.properties")
public class ApplicationConfig {
    @Value("${db.url}")
    private String url;
    @Value("${db.user}")
    private String user;
    @Value("${db.password}")
    private String password;
    @Value("${db.driver.name}")
    private String driver;

    @Bean
    public HikariDataSource hikariDataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(url);
        ds.setUsername(user);
        ds.setPassword(password);
        ds.setDriverClassName(driver);

        return ds;
    }

    @Bean
    public DriverManagerDataSource driverManagerDataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(url);
        ds.setUsername(user);
        ds.setPassword(password);
        ds.setDriverClassName(driver);

        return ds;
    }
}
