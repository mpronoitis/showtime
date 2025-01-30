package com.app.showtime.config;

import com.app.showtime.domain.User;
import com.app.showtime.dto.UserClaimsDTO;
import com.app.showtime.enumeration.ErrorType;
import com.app.showtime.error.exception.GenericBadRequestException;
import com.app.showtime.repository.UserRepository;
import com.app.showtime.service.utils.Utils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import static java.lang.String.format;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenUtil {

    private final UserRepository userRepository;

    private static final String CLAIMS_USER_CLAIMS_DTO = "userClaims";
    private static final String CLAIMS_IS_AUTHENTICATION = "isAuthentication";

    @Value("${application.jwt.secret}")
    private String jwtSecret;

    @Value("${application.jwt.expiration-millis}")
    private Integer jwtExpirationMillis;


    public String generateAccessToken(UserClaimsDTO user) {
        Header<?> header = Jwts.header();
        header.setType(Header.JWT_TYPE);
        user.setPassword(null);
        Map<String, Object> userClaims = new HashMap<>();
        userClaims.put(CLAIMS_IS_AUTHENTICATION, true);
        userClaims.put(CLAIMS_USER_CLAIMS_DTO, user);
        return Jwts.builder()
                .setHeader((Map<String, Object>) header)
                .setSubject(format("%s,%s", user.getId(), user.getUsername()))
                .setIssuer("localhost")
                .setIssuedAt(Utils.convertToDateViaInstant(LocalDateTime.now()))
                .addClaims(userClaims)
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMillis))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public UserClaimsDTO getUserClaimsDTO(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

        return new ObjectMapper().convertValue(claims.get(CLAIMS_USER_CLAIMS_DTO), new TypeReference<UserClaimsDTO>() {
        });
    }

    public boolean validate(String token) {
        try {
            UserClaimsDTO userClaimsDTO = getUserClaimsDTO(token);
            User user = userRepository.findByUsername(userClaimsDTO.getUsername()).orElseThrow(() -> new GenericBadRequestException("",ErrorType.IM_USER_NOT_FOUND));
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature - {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token - {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            log.warn("Expired JWT token - {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token - {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty - {}", ex.getMessage());
        }
        return false;
    }

}
