package com.cachenote.server.security.service;
import com.cachenote.server.security.entity.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;



@Service
public class JwtService {


    //todo: shouldn't store SECRET_KEY here in prod
    private static final String SECRET_KEY = "d7845cda363939a0f7bc528c6056d0c9eb883d432d7910eeb59999125598ed40";


    public Long extractUserId(String token) {
        final Claims claims = extractAllClaims(token);
        String userIdString = claims.getSubject();
        return Long.parseLong(userIdString);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetailsImpl userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetailsImpl userDetails
    ) {
        return Jwts.
                builder().
                setClaims(extraClaims)
                .setSubject(userDetails.getId().toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetailsImpl userDetails) {
        final Long userId = extractUserId(token);
        return (userId.equals(userDetails.getId())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
