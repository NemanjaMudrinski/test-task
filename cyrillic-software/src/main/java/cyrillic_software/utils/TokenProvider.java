package cyrillic_software.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import cyrillic_software.security.UserPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class TokenProvider {
	
	@Value("${token.secret}")
	private String secret;
	
	@Value("${token.expiration}")
	private Long expiration;
	
    private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

      
    public String createToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("sub", userPrincipal.getId());
        claims.put("uniq", userPrincipal.getEmail());
        claims.put("created", new Date(System.currentTimeMillis()));
    	claims.put("role", authentication.getAuthorities());
        
    	return Jwts.builder().setClaims(claims).setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
				.signWith(SignatureAlgorithm.HS512, this.secret).compact();
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(this.secret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }

}
