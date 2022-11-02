package com.example.imbee;

import com.example.imbee.repository.BaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
@EnableWebMvc
public class ImbeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImbeeApplication.class, args);
	}

}
