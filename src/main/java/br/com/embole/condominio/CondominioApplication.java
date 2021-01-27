package br.com.embole.condominio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class CondominioApplication {

	public static void main(String[] args) {
		SpringApplication.run(CondominioApplication.class, args);
	}

}
