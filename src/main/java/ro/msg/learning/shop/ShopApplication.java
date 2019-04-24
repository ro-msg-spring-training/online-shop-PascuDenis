package ro.msg.learning.shop;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ResourceBundle;

@SpringBootApplication
public class ShopApplication {

	public static void main(String[] args) {
//		SpringApplication.run(ShopApplication.class, args);
		ResourceBundle applicationProperties = ResourceBundle.getBundle("flyway");
		Flyway flyway = new Flyway();
		flyway.setDataSource(applicationProperties.getString("flyway.url"), applicationProperties.getString("flyway.user"), applicationProperties.getString("flyway.password"));
		flyway.migrate();
//		Flyway flyway = Flyway.configure().dataSource("jdbc:h2:C:\\Users\\pascud\\test", "sa", null).load();
//		flyway.migrate();
	}

}
