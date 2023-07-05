package io.github.kleberbarilli.msicards;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@EnableRabbit
public class MsicardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsicardsApplication.class, args);
	}

}
