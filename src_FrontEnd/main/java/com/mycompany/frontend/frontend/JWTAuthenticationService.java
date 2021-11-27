/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.frontend.frontend;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.AbstractMap;
import java.util.Date;
import java.util.Map.Entry;
import static javax.crypto.Cipher.SECRET_KEY;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author student
 */
public class JWTAuthenticationService {
    SignatureAlgorithm signatureAlgorithm;
    
    String secretString;
    
    public JWTAuthenticationService (){
        signatureAlgorithm = SignatureAlgorithm.HS256;
        
        secretString = Encoders.BASE64.encode("bonjourbonjourbonjourbonjourbonjourbonjour".getBytes());
    }
    
    public String createJWT(String issuer, String subject, long ttlMillis){
        
        // JWT signature used to sign token
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretString);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        
        // Setting JWT Claims
        JwtBuilder builder;
        builder = Jwts.builder().setIssuedAt(now).setSubject(subject).setIssuer(issuer).signWith(signingKey);
        
        if (ttlMillis > 0){
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        
        String a = builder.compact();
        System.out.println(a);
        
        // Build JWT nad serialize it to a compact, URL-safe string
        return a;
    }
    
    public Entry<Boolean, String> verify(String jwt) throws UnsupportedEncodingException {
        Jws<Claims> jws = null;
        String username="";
        System.out.println("Verfying: "+ jwt);
        try {
            jws = Jwts.parserBuilder()
                    .setSigningKey("bonjourbonjourbonjourbonjourbonjourbonjour")
                    .build()
                    .parseClaimsJws(jwt);
            
            System.out.println("We can safely trust the JWT");
            username=jws.getBody().getSubject();
            System.out.println("Username: " + username);
        } catch (JwtException ex){
            System.out.println("We cannot use the JWT as intended by its creator");
        }
        Entry entry;
        if (jws == null){
            entry = new AbstractMap.SimpleEntry<Boolean, String>(false, "");
            return entry;
        }
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        
        if (jws.getBody().getExpiration().before(now)){
            entry = new AbstractMap.SimpleEntry<Boolean, String>(false, "");
            return entry;
        }
        
        entry = new AbstractMap.SimpleEntry<Boolean, String>(true, username);
        return entry;
    }
}
