package br.edu.ifpb.pweb2.sisyphus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SisyphusApplication {

	public static void main(String[] args) {
		SpringApplication.run(SisyphusApplication.class, args);
	}

}
