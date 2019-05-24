package ro.msg.learning.shop;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication//	(exclude = ErrorMvcAutoConfiguration.class)
public class ShopApplication {
	private static final Logger logger = Logger.getLogger(ShopApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);

	 	System.out.println("merge");
		logger.info("SALLLLLTI");
	}
}

