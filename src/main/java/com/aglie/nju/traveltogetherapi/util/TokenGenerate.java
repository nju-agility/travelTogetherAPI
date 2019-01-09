package com.aglie.nju.traveltogetherapi.util;

import com.sun.corba.se.impl.orb.ORBDataParserImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import javax.xml.crypto.Data;
import java.security.Key;
import java.security.Signature;
import java.util.Date;

public class TokenGenerate {
    /**
     * 签名秘钥
     */
    public static final String SECRET = "admin";

    /**
     * 生成Token
     * @param account 用户名
     * @return
     */
    public static String createJwtToken(String account){
        String issuer = "";
        String subject = "";
        long ttlMillis = 3600000;
        return createJwtToken(account, issuer, subject, ttlMillis);
    }

    /**
     *生成Token
     * @param account 用户名
     * @param issuer 该JWT的签发者，是否使用是可选的
     * @param subject 该JWT所面向的用户，是否使用是可选的
     * @param ttlMillis 签发时间（有效时间，过期会报错）
     * @return token String
     */
    public static String createJwtToken(String account, String issuer, String subject, long ttlMillis){
        //签名算法，将对Token进行签名
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //生成签发时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //通过秘钥签名JWT
        byte[] apiKeySecreteBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecreteBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setId(account)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        if (ttlMillis > 0){
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        return builder.compact();
    }

    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

}
