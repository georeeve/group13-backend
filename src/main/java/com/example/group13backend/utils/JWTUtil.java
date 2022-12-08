package com.example.group13backend.utils;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.security.PublicKey;

@Service
public class JWTUtil {

    private final JwtBuilder jwtBuilder;
    private final JwtParser jwtParser;

    public JWTUtil(KeyUtil keyUtil) {
        PrivateKey privateKey = keyUtil.getPrivateKey();
        PublicKey publicKey = keyUtil.getPublicKey();

        jwtBuilder = Jwts.builder().signWith(privateKey, SignatureAlgorithm.ES256);
        jwtParser = Jwts.parserBuilder().setSigningKey(publicKey).build();
    }

    public String sign(Long userID) {
        return jwtBuilder.setSubject(userID.toString()).compact();
    }

    public String verify(String jws) {
        return jwtParser.parseClaimsJws(jws).getBody().getSubject();
    }
}
