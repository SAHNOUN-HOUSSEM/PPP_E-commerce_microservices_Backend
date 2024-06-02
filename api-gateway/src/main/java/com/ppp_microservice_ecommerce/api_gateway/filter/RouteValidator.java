package com.ppp_microservice_ecommerce.api_gateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<Map<String, String>> openApiRoutes = List.of(
            Map.of("route", "/auth/login", "method", "POST"),
            Map.of("route", "/auth/register", "method", "POST"),
            Map.of("route", "/auth/validate", "method", "POST"),
            Map.of("route", "/product", "method", "GET"),
            Map.of("route", "/brand", "method", "GET"),
            Map.of("route", "/brand/{id}", "method", "GET"),
            Map.of("route", "/brand/{id}/products", "method", "GET"),
            Map.of("route", "/category", "method", "GET"),
            Map.of("route", "/category/{id}", "method", "GET"),
            Map.of("route", "/category/{id}/products", "method", "GET")
    );



    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiRoutes
                    .stream()
                    .noneMatch(
                            map -> request.getURI().getPath().contains(map.get("route"))
                                    && request.getMethod().name().equals(map.get("method"))
                    );

}
