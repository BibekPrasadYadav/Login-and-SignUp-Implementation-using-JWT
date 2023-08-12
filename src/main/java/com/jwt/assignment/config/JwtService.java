package com.jwt.assignment.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
  private static final String SECRET_KEY="1HdiMYYX/nAnw9RWD+DCjdkinhN+Mec9VxiYZOsOuNJS+vCy9vt+bqFEdSxxVsE7ZrZgCjWiEK2gDuy21Y26Hgn+o3dluBX4+XsRo/6mqgoUUZss6dZ4UCXCDofoYWo3RmxSWdI9I9yFIss8pakJnXRdOKIyLo2DcKlgmOzD+dAr2c2U2B9k78q/JSA6/Y2Gr3amtXcgrC8glo6G+c7xQbqT5qsTP/IlxwvJVEcli/8NNUfre8Zogyi8rEdX26RYc2k6HHssnw/f1lHsaEsWPY1DFqbWiL6rFSnpsb04ZRfCy+JNaxhqtQSVCqgBIxTDMbRkuQCuNVfQ3sz8TyYkOBPYK8LKTe2xiLWDeeYT0gw=";
  public String extractUsername(String token) {
    return extractClaims(token,Claims::getSubject);
  }

  public <T> T extractClaims(String token, Function<Claims,T> claimsResolver){
    final Claims claim=extractAllClaims(token);
    return claimsResolver.apply(claim);
  }

  public String generateToken(UserDetails userDetails){
    return generateToken(new HashMap<>(),userDetails);
  }
  public String generateToken(
          Map<String,Object> extraclaims,
          UserDetails userDetails
  ){
    return Jwts.builder().setClaims(extraclaims).
            setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();
  }


  public boolean isTokenValid(String token,UserDetails userDetails){
    final String username=extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaims(token,Claims::getExpiration);
  }

  private Claims extractAllClaims(String token){


    return Jwts.parserBuilder()
            .setSigningKey(getSignInKey())
            .build().parseClaimsJws(token).getBody();
  }

  private Key getSignInKey() {
    byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
