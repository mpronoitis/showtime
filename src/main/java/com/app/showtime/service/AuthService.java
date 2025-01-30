package com.app.showtime.service;

import com.app.showtime.config.JwtTokenUtil;
import com.app.showtime.dto.UserClaimsDTO;
import com.app.showtime.dto.request.AuthRequestDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenUtil jwtTokenUtil;
    private final DaoAuthenticationProvider authenticationProvider;
    @Transactional(dontRollbackOn = BadCredentialsException.class)
    public String  login(AuthRequestDTO authRequestDTO) {
        UserClaimsDTO userClaimsDTO;
        try {
            Authentication authenticate = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
            userClaimsDTO = (UserClaimsDTO) authenticate.getPrincipal();
        } catch (BadCredentialsException ex) {
            throw ex;
        }

        //Generate token for the user
        String jwtToken = jwtTokenUtil.generateAccessToken(userClaimsDTO);
        return jwtToken;
    }
}
