package com.example.group13backend.utils;

import com.example.group13backend.logging.ErrorMessage;
import com.example.group13backend.logging.Logger;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.PrivateKey;
import java.security.PublicKey;
import org.springframework.stereotype.Service;

@Service
public class JWTUtil {

  private final JwtBuilder jwtBuilder;
  private final JwtParser jwtParser;
  private final Logger logger;
  private final SnowflakeUtil snowflakeUtil;

  public JWTUtil(KeyUtil keyUtil, Logger logger, SnowflakeUtil snowflakeUtil) {
    PrivateKey privateKey = keyUtil.getPrivateKey();
    PublicKey publicKey = keyUtil.getPublicKey();

    this.jwtBuilder = Jwts.builder().signWith(privateKey, SignatureAlgorithm.ES256);
    this.jwtParser = Jwts.parserBuilder().setSigningKey(publicKey).build();
    this.logger = logger;
    this.snowflakeUtil = snowflakeUtil;
  }

  public String sign(Long userID) {
    return jwtBuilder.setSubject(userID.toString()).claim("ses", snowflakeUtil.newId()).compact();
  }

  public String getTokenFromHeader(String authorization) {
    final var tokenSplit = authorization.split("Bearer ");
    if (tokenSplit.length != 2) {
      logger.error(ErrorMessage.TOKEN_INVALID);
      return null;
    }
    return tokenSplit[1];
  }

  public Long getUserId(String jws) {
    try {
      return Long.parseLong(jwtParser.parseClaimsJws(jws).getBody().getSubject());
    } catch (Exception exception) {
      logger.error(ErrorMessage.TOKEN_INVALID);
      return null;
    }
  }

  public Long getSessionId(String jws) {
    try {
      return jwtParser.parseClaimsJws(jws).getBody().get("ses", Long.class);
    } catch (Exception exception) {
      logger.error(ErrorMessage.TOKEN_INVALID);
      return null;
    }
  }
}
