package com.linknote.server.security.filter;

import com.linknote.server.common.exception.TokenNotProvidedException;
import com.linknote.server.security.entity.UserDetailsImpl;
import com.linknote.server.security.service.JwtService;
import com.linknote.server.security.service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final Long userId;
        // If there is no token in header throw an Exception
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new TokenNotProvidedException(null);
        }
        // else, valid the token.
        jwt = authHeader.substring(7);
        userId = jwtService.extractUserId(jwt);
        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // todo: should grad userDetails from Redis rather than postgres
            UserDetailsImpl userDetails = this.userDetailsService.loadUserByUserId(userId);
            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);

    }

    // private static final String[] SWAGGER_PATHS = {"/swagger-ui.html",
    // "/v3/api-docs/**", "/swagger-ui/**", "/webjars/swagger-ui/**",
    // "/context-path/swagger-ui.html", "/context-path/v3/api-docs", "/api-docs"};

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        List<String> urls = Arrays.asList("/api/v1/health", "/api/v1/auth/**");

        for (String url : urls) {
            if (antPathMatcher.match(url, path)) {
                return true;
            }
        }

        return false;
    }
}