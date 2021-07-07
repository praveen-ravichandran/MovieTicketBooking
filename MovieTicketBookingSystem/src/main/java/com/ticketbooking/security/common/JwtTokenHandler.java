package com.ticketbooking.security.common;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class JwtTokenHandler {

    private static Jws<Claims> parseJwt(String jwtString) {

        String secret = System.getenv("JWT_HMAC_SECRET_KEY");

        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret), SignatureAlgorithm.HS256.getJcaName());

        Jws<Claims> jwt = Jwts.parserBuilder()
                .setSigningKey(hmacKey)
                .build()
                .parseClaimsJws(jwtString);

        return jwt;
    }
    
    public static Map<String, Object> getJwtClaims(String jwtString) {
    	Jws<Claims> claims = parseJwt(jwtString);
    	return claims.getBody();
    }
    
    public static Boolean isTokenValid(String jwtString) {
    	try {
    		parseJwt(jwtString);
    	} catch (Exception e) {
    		return false;
    	}
    	return true;
    }

    public static String createJwtSignedHMAC(String userId, String emailAddress) {

        String secret = System.getenv("JWT_HMAC_SECRET_KEY");

        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret), SignatureAlgorithm.HS256.getJcaName());

        Instant now = Instant.now();
        String jwtToken = Jwts.builder()
                .claim("id", userId)
                .claim("email", emailAddress)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(60l, ChronoUnit.MINUTES)))
                .signWith(hmacKey)
                .compact();

        return jwtToken;
    }

}