package com.orderService.apigateway.jwtfilter;

import com.orderService.apigateway.utilites.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
    public class JwtFilter implements GlobalFilter, Ordered {

        private final JwtUtil jwtUtil;

        public JwtFilter(JwtUtil jwtUtil) {
            this.jwtUtil = jwtUtil;
        }

        @Override
        public Mono<Void> filter(ServerWebExchange exchange,
                                 GatewayFilterChain chain) {

            String path = exchange.getRequest().getURI().getPath();

            // Public APIs ko bypass karo
            if (path.startsWith("/public")) {
                return chain.filter(exchange);
            }

            String authHeader =
                    exchange.getRequest()
                            .getHeaders()
                            .getFirst(HttpHeaders.AUTHORIZATION);

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            try {

                String jwt = authHeader.substring(7);

                String username = jwtUtil.extractUsername(jwt);

                if (username == null || !jwtUtil.validateToken(jwt)) {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }

                // Optional: downstream services ko username pass karo
                ServerWebExchange modifiedExchange =
                        exchange.mutate()
                                .request(exchange.getRequest()
                                        .mutate()
                                        .header("X-Authenticated-User", username)
                                        .build())
                                .build();

                return chain.filter(modifiedExchange);

            } catch (Exception e) {

                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

        }

    @Override
    public int getOrder() {
        return -1;
    }
}
