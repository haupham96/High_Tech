package jwt.config;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component

public class JwtUtil implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    private static final String secretKey = "jasmine";

    public String generateJwt(String userName) {
        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date().getTime() + (60 * 60 * 24 * 100))))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String getUserNameFromJwt(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwt(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
            return true ;
        } catch (SignatureException signatureException) {
            logger.error("invalid Signature ", signatureException.getMessage());
        } catch (MalformedJwtException malformedJwtException){
            logger.error("invalid Jason Web Token ",malformedJwtException.getMessage());
        }catch (ExpiredJwtException expiredJwtException){
            logger.error("Token is expired ",expiredJwtException.getMessage());
        }catch (UnsupportedJwtException unsupportedJwtException){
            logger.error("Token is unsupported ",unsupportedJwtException.getMessage());
        }catch (IllegalArgumentException illegalArgumentException){
            logger.error("Jwt is empty ",illegalArgumentException.getMessage());
        }
        return false ;
    }


}
