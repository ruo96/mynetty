package com.wrh.utils;


//import io.jsonwebtoken.*;
/**
 * @author wuruohong
 * @Classname JwtUtils
 * @Description TODO
 * @Date 2021/11/15 18:00
 */
public class JwtUtils {


    private String createJWT(String id, String issuer, String subject, long ttlMillis) {
        return "";

/*//The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        LocalDate now = LocalDate.now();

//We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(apiKey.getSecret());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

//if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

//Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();*/
    }


    /** 验证方式*/
    /*import javax.xml.bind.DatatypeConverter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

    //Sample method to validate and read the JWT
    private void parseJWT(String jwt) {
//This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(apiKey.getSecret()))
                .parseClaimsJws(jwt).getBody();
        System.out.println("ID: " + claims.getId());
        System.out.println("Subject: " + claims.getSubject());
        System.out.println("Issuer: " + claims.getIssuer());
        System.out.println("Expiration: " + claims.getExpiration());*/
    }


