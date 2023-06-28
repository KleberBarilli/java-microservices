package io.github.kleberbarilli.investxserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class InvestxserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvestxserverApplication.class, args);
	}

}
