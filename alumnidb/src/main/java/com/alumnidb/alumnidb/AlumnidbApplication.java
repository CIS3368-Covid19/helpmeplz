package com.alumnidb.alumnidb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EntityScan(basePackages = {"com.alumnidb.alumnidb.entity"})
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class AlumnidbApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlumnidbApplication.class, args);
	}

}
