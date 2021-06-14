package com.scopegroup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author maysara.mohammed
 * @version 1.0
 * @since 2021-06-11
 */

@EnableSwagger2
@SpringBootApplication
public class PublicationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PublicationsApplication.class, args);
	}

}
