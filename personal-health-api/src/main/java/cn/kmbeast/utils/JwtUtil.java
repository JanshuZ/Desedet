package cn.kmbeast.utils;


import io.jsonwebtoken.*;

import java.util.Date;
import java.util.UUID;

/**
 *JWT token utility class
 */
public class JwtUtil {
    /**
     *Key
     */
    private static final String privateKey = "d8c986df-8512-42b5-906f-eeea9b3acf86";
    /**
     *Valid for one week -->timestamp
     */
    private static final Integer time = 1000 * 60 * 60 * 24 * 7;

    /**
     *Generate token
     *@ param id User ID
     * @return String
     */
    public static String toToken(Integer id, Integer role) {
        JwtBuilder jwtBuilder = Jwts.builder();
        return jwtBuilder.setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .claim("id", id)
                .claim("role", role)
                .setSubject("用户认证")
                .setExpiration(new Date(System.currentTimeMillis() + time))
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS256, privateKey)
                .compact();
    }

    /**
     *Decrypt TOKEN
     *@ param token tokenInformation
     */
    public static Claims fromToken(String token) {
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJws;
        try {
            claimsJws = jwtParser.setSigningKey(privateKey).parseClaimsJws(token);
            return claimsJws.getBody();
        } catch (Exception e) {
            return null;
        }
    }

}
