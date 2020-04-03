package io.agilehandy.frontend;

import reactor.core.publisher.Mono;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class FrontendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontendApplication.class, args);
	}

	@LoadBalanced
	@Bean
	WebClient.Builder webClientBuilder() {
		return WebClient.builder();
	}
}

@RestController
class MyController {

	private final WebClient.Builder loadBalancedWebClientBuilder;

	MyController(WebClient.Builder builder) {
		this.loadBalancedWebClientBuilder = builder;
	}


	@GetMapping
	public Mono<String> fromBackendService() {
		return loadBalancedWebClientBuilder.build()
				.get().uri("http://backend-service/message")
				.retrieve().bodyToMono(String.class);
	}

}


