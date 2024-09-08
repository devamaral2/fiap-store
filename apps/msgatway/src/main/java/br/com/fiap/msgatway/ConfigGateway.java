package br.com.fiap.msgatway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigGateway {

    private final HeaderCheckFilter headerCheckFilter;

    public ConfigGateway (HeaderCheckFilter headerCheckFilter) {
        this.headerCheckFilter = headerCheckFilter;
    }
    @Bean
    public RouteLocator custom(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("cliente", r -> r.path("/cliente/**")
                        .filters((f) -> f.filters(headerCheckFilter))
                        .uri("http://localhost:8081"))
                .route("produto", r -> r.path("/produto/**")
                        .and()
                        .not( p -> p.path("/produto/api/**"))
                        .filters(f -> f)
                        .uri("http://localhost:8082"))
                .route("pedido", r -> r.path("/pedido/**")
                        .and()
                        .not( p -> p.path("/pedido/api/**"))
                        .filters(f -> f)
                        .uri("http://localhost:8083"))
                .route("entrega", r -> r.path("/entrega/**")
                        .and()
                        .not( p -> p.path("/entrega/api/**"))
                        .filters(f -> f)
                        .uri("http://localhost:8084"))
                .build();
    }
}
