package com.ppp_microservice_ecommerce.api_gateway.filter;

import com.ppp_microservice_ecommerce.api_gateway.service.JwtService;
import com.ppp_microservice_ecommerce.api_gateway.utils.JwtUtils;
import com.ppp_microservice_ecommerce.clients.auth.AuthClient;
import com.ppp_microservice_ecommerce.clients.auth.ValidateTokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    private RouteValidator routeValidator;
    @Autowired
    private AuthClient authClient ;

    private JwtUtils jwtUtils;
    private JwtService jwtService;
    public AuthenticationFilter() {
        super(Config.class);
        this.routeValidator = new RouteValidator();
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if(routeValidator.isSecured.test(exchange.getRequest())) {
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    System.out.println("no authorization header found");
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }
                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if(authHeader!=null && authHeader.startsWith("Bearer")) {
                    String token = authHeader.substring(7);
                    Boolean isValid= authClient.validateToken(new ValidateTokenDto(token));
                    if(!isValid){
                        System.out.println("Token is invalid");
                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                        return exchange.getResponse().setComplete();
                    }
                } else {
                    System.out.println("no token found in header");
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {
        // Put configuration properties here
    }
}
