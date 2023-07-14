package com.chatgptgoestoschool.recipediscovery.utils;

import java.security.Key;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JWT {
  @Value("${jwt.secret}")
  private String secret;

  public Jws<Claims> decodeToken(String token) {
    String auth = token.replace("Bearer ", "");
    Key key = Keys.hmacShaKeyFor(secret.getBytes());
    return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(auth);
  }
}
