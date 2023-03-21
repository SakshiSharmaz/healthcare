package com.healthcare.config;



import com.healthcare.constants.ApiConstants;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 *  JwtAuthenticationEntryPoint is called whenever an exception is thrown due to an unauthenticated
 *  user trying to access a resource that requires authentication.
 */

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {

        JSONObject response = new JSONObject();
        logger.error("Responding with unauthorized error. Message - {}", e.getMessage());

        if (e.getMessage().equalsIgnoreCase(ApiConstants.USER_ID_NOT_FOUND)
                || e.getMessage().equalsIgnoreCase(ApiConstants.WRONG_PASSWORD)) {

            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            httpServletResponse.setContentType(ApiConstants.REST_JSON_CONTENT_TYPE);
            response.put(ApiConstants.MESSAGE, "Bad Credentials!");

        } else {

            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpServletResponse.setContentType(ApiConstants.REST_JSON_CONTENT_TYPE);
        }
    }
}
