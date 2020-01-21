package com.adiSuper.autha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.adiSuper.generated.core.tables.pojos")
public class AuthaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthaApplication.class, args);
	}

}
