package app.bank.loanEvaluation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LoanEvaluationApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanEvaluationApplication.class, args);
	}

}
