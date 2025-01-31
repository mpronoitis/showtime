package com.app.showtime.config;

import com.app.showtime.enumeration.ErrorType;
import com.app.showtime.error.exception.NotFoundException;
import com.app.showtime.repository.PermissionRepository;
import com.app.showtime.repository.RolePermissionsRepository;
import com.app.showtime.repository.RoleRepository;
import com.app.showtime.repository.UserRepository;
import com.app.showtime.service.UserClaimsService;
import com.app.showtime.service.utils.Utils;
import com.app.showtime.shared.rest.model.ApiResponse;
import com.app.showtime.utils.UserSpecification;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import java.io.IOException;


@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@EnableWebSecurity
public class WebSecurityConfig {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RolePermissionsRepository rolePermissionsRepository;
    private final UserClaimsService userClaimsService;
    private final UserRepository userRepository;
    private final JwtTokenFilter jwtTokenFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Enable CORS and disable CSRF
        http.cors().and().csrf().disable();

        // Set session management to stateless
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Set unauthorized requests exception handler
        http
                .exceptionHandling()
                .authenticationEntryPoint(this::handleNotFound);

        // Set authentication provider
        http.authenticationProvider(authenticationProvider());

        // Authorize all request from public/*
        http.authorizeHttpRequests(authorize ->
                authorize
                        .requestMatchers("/public/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated());

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService(username -> userClaimsService.getUserClaimsDTOByUserDomain(userRepository.findOne(UserSpecification.haveUsername(username)).orElseThrow(NotFoundException::new)) );
        return authProvider;
    }

    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }

    private void handleNotFound(HttpServletRequest request, HttpServletResponse response, Exception ex) throws IOException {
        ApiResponse<Null> unauthorizedResponse =
                new ApiResponse.Builder<Null>(ErrorType.IM_GENERIC.getCode(), false)
                        .errorMessage(ex.getMessage())
                        .build();
        Utils.changeResponse(response, HttpStatus.NOT_FOUND.value(), unauthorizedResponse);
    }
}
