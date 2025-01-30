package com.app.showtime.config;

import com.app.showtime.enumeration.ErrorType;
import com.app.showtime.service.utils.Utils;
import com.app.showtime.shared.rest.model.ApiResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static java.util.List.of;
import static java.util.Optional.ofNullable;
;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (ObjectUtils.isEmpty(header) || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        //Find the token and validate it
        final String token = header.split(" ")[1].trim();
        if (!jwtTokenUtil.validate(token)) {
            ApiResponse<Null> unauthorizedResponse = new ApiResponse.Builder<Null>(ErrorType.IM_UNAUTHORIZED_JWT.getCode(), false).errorMessage(ErrorType.IM_UNAUTHORIZED_JWT.getMessage()).build();

            Utils.changeResponse(response, HttpStatus.UNAUTHORIZED.value(), unauthorizedResponse);
        }

        UserDetails userDetails = jwtTokenUtil.getUserClaimsDTO(token);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, ofNullable(userDetails).map(UserDetails::getAuthorities).orElse(of()));

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
