package com.br.gateway.payments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayPaymentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayPaymentsApplication.class, args);
	}

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/api/payments")
                        .uri("http://localhost:7074")).
                build();
    }

}
