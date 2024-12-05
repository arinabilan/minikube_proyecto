package app.bank.solicitude;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SolicitudeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolicitudeApplication.class, args);
	}

}
