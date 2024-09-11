package br.com.fiap.msgatway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigGateway {

    private final HeaderCheckFilter headerCheckFilter;

    private final String authServiceUri = "http://localhost:8081";

    public ConfigGateway (HeaderCheckFilter headerCheckFilter) {
        this.headerCheckFilter = headerCheckFilter;
    }
    @Bean
    public RouteLocator custom(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth", r -> r.path("/auth/**")
                        .uri(authServiceUri))
                .route("products", r -> r.path("/product/**")
                        .filters((f) -> f.filters(headerCheckFilter))
                        .uri(authServiceUri))
                .route("cart", r -> r.path("/cart/**")
                        .filters((f) -> f.filters(headerCheckFilter))
                        .uri(authServiceUri))
                .route("payment", r -> r.path("/payment/**")
                        .filters((f) -> f.filters(headerCheckFilter))
                        .uri(authServiceUri))
                .build();
    }
}
