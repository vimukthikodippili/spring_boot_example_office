/*
 *
 *  *  *  * ---------------------------------------------------------------------------------------------
 *  *  *  *  *  Copyright (c) IJSE-intern. All rights reserved.
 *  *  *  *  *  Licensed under the MIT License. See License.txt in the project root for license information.
 *  *  *  *  --------------------------------------------------------------------------------------------/
 *
 */

package com.example.customercrud.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 5/28/2021
 **/

@Component
public class JwtUtil {

    private String SECRET_KEY = "secret";

    public String extractEmail(String token){

        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

//    public String generateToken(UserDetails userDetails){
//        Map<String, Object> claims = new HashMap<>();
//        return creteToken(claims,userDetails.getUsername());
//    }
//
//    private String creteToken(Map<String, Object> claims, String subject){
//        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
//                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
//    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String userName = extractEmail(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
}
