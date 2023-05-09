package com.example.studentenrollment.security;
import com.example.studentenrollment.model.User;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${studentenrollment.jwt.secret}")
    private String jwtSecret;

    @Value("${studentenrollment.jwt.expiry}")
    private int jwtExpiry;

    public String generateJwtToken(Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .addClaims(Map.of("userType", userPrincipal.getUserType()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpiry))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUsertypeFromToken(String token) {
        return (String) Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().get("userType");
    }
    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean isValidToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT has expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT is unsupported: {}", e.getMessage());
        } catch (MalformedJwtException | IllegalArgumentException e){
            logger.error("Invalid JWT token: {}", e.getMessage());
        }

        return false;
    }
}
