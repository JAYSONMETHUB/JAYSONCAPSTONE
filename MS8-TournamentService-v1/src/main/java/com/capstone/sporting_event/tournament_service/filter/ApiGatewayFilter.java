package com.capstone.sporting_event.tournament_service.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ApiGatewayFilter extends OncePerRequestFilter {

    private static final String API_GATEWAY_HEADER = "X-Forwarded-Host";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException , IOException {
        String apiGatewayHeader = request.getHeader(API_GATEWAY_HEADER);
        if(apiGatewayHeader == null || !apiGatewayHeader.equals("localhost:8080")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return;
        }

        filterChain.doFilter(request,response);

    }
}
