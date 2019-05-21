package ro.msg.learning.shop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
public class SomeOrderConfig {

    @Autowired
    private Environment env;

    @Bean
    @Profile("test")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.test.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.test.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.test.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.test.datasource.password"));


        return dataSource;
    }
}
