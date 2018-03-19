package co.simplon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({"co.simplon.dao"})
@EntityScan("co.simplon.dao")

public class CsibackApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsibackApplication.class, args);
	}
}
