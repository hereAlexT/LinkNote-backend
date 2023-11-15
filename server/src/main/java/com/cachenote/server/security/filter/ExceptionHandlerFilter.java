package com.cachenote.server.security.filter;

import com.cachenote.server.common.StatusCode;
import com.cachenote.server.common.exception.TokenNotProvidedException;
import com.cachenote.server.security.FilterResponseWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * This filter needs to be put in the front of the filter chain.
 * This method is inspired by
 * <a href="https://stackoverflow.com/questions/34595605/how-to-manage-exceptions-thrown-in-filters-in-spring">How to manage exceptions thrown in filters in Spring? | Stackoverflow</a>
 */
@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (TokenNotProvidedException e) {
            logger.debug(e.getMessage());
            buildJsonResponse(response, HttpStatus.UNAUTHORIZED.value());
            FilterResponseWrapper<String> wrapper = FilterResponseWrapper.Result(StatusCode.NO_JWT_TOKEN.getCode(), e.getMessage(), null);
            response.getWriter().write(objectMapper.writeValueAsString(wrapper));
        } catch (ExpiredJwtException e) {
            logger.debug(e.getMessage());
            buildJsonResponse(response, HttpStatus.UNAUTHORIZED.value());
            FilterResponseWrapper<String> wrapper = FilterResponseWrapper.Result(StatusCode.EXPIRED_JWT_TOKEN.getCode(), "JWT TokenCache Expired", null);
            response.getWriter().write(objectMapper.writeValueAsString(wrapper));
        }
        catch (Exception e) {
            logger.debug(e.getMessage());
            buildJsonResponse(response, HttpStatus.INTERNAL_SERVER_ERROR.value());
            FilterResponseWrapper<String> wrapper = FilterResponseWrapper.Result(StatusCode.INTERNAL_SERVER_ERROR.getCode(), "System Error " + e.getMessage(), null);
            response.getWriter().write(objectMapper.writeValueAsString(wrapper));
        }
    }

    private static void buildJsonResponse(HttpServletResponse response, int status) {
        response.setStatus(status);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }
}
