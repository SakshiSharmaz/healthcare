package com.healthcare.config;



//import com.lattice.constants.ApiConstants;
//import com.lattice.constants.DBconstants;
//import com.lattice.model.Hospital;
//import com.lattice.model.UserPrincipal;
//
//import com.lattice.repository.UserRepository;
import com.healthcare.constants.ApiConstants;
import com.healthcare.constants.DBconstants;
import io.jsonwebtoken.*;

import com.healthcare.model.UserPrincipal;
import com.healthcare.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

/*
 * JwtTokenProvider class will be used for generating a JWT after a user logs in successfully, and
 * validating the JWT sent in the Authorization header of the requests
 */

@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Autowired
    UserRepository userRepository;

    public String generateToken(Authentication authentication) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Claims claims = Jwts.claims();
        claims.put(DBconstants.USER_ID, userPrincipal.getUuid());
        claims.put(DBconstants.FULL_NAME, userPrincipal.getLastName() + ", "  + userPrincipal.getFirstName());

        return Jwts.builder().setClaims(claims).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }


    public UUID getUserIdFromJwt(String token) {
        if(token.contains("Bearer"))
            token = token.substring(7);
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

        return (UUID) claims.get(DBconstants.USER_ID);
    }

    public Integer getHospitalIdFromJwt(String token) {
        if(token.contains("Bearer"))
            token = token.substring(7);
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

        return (Integer) claims.get(DBconstants.HOSPITAL_ID);
    }

    public int getUserPriIdFromJwt(String token) {
        if(token.contains("Bearer"))
            token = token.substring(7);
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

        return (int) claims.get(DBconstants.USER_PRI_ID);
    }

    public boolean validateToken(String authToken) {

        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
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

    public Claims getAllClaimsFromToken(String token) {

        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }

        return claims;
    }

    public String getUsername(String header) {

        Claims claim = getAllClaimsFromToken(header.split(" ")[1]);

        return claim.get(DBconstants.FULL_NAME).toString();
    }

    public int getUserId(String header) {

        Claims claim = getAllClaimsFromToken(header.split(" ")[1]);
        return (int) claim.get(DBconstants.USER_PRI_ID);

    }

    String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(ApiConstants.AUTHORIZATION);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(ApiConstants.BEARER)) {

            return bearerToken.substring(7, bearerToken.length());

        } else {

            return null;
        }
    }


    public String generateToken(String userId) {
        UserPrincipal userPrincipal = userRepository.findByUuid(UUID.fromString(userId));
        Claims claims = Jwts.claims();
//        claims.put(DBconstants.USER_PRI_ID, userPrincipal.getUserPriId());
        claims.put(DBconstants.USER_ID, userPrincipal.getUuid());
        claims.put(DBconstants.FULL_NAME, userPrincipal.getLastName() + ", "  + userPrincipal.getFirstName());
        claims.put(DBconstants.SECRET, userPrincipal.getPassword());

        return Jwts.builder().setClaims(claims).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 600000 ))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

    }

    public String getPasswordHashFromToken(String header) {

        Claims claim = getAllClaimsFromToken(header.split(" ")[1]);
        return claim.get(DBconstants.SECRET).toString();

    }
}

