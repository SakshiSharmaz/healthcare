package com.healthcare.config;



import com.healthcare.model.UserPrincipal;
import com.healthcare.service.impl.CustomUserDetailsImpl;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.UUID;

/*
 * CustomUserDetailsService extends AuthenticationProvider which includes user checks in
 * the database (including active/inactive) state of user.
 */

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsImpl userService;

    @Value("${password.secret}")
    String secret;

    @SneakyThrows
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String userId = authentication.getName();
        String password = (String) authentication.getCredentials();

        UserPrincipal user = userService.loadUserById(UUID.fromString(userId));

        if (user == null) {

            return null;

        } else {
            boolean matches = bCryptPasswordEncoder.matches(password, user.getPassword());
            if (!matches) {

                return null;

            } else {

                Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

                return new UsernamePasswordAuthenticationToken(user, password, authorities);
            }
        }
    }

//    @SneakyThrows
//    public Authentication authenticate(Authentication authentication, BaseState forgotPassword) throws AuthenticationException {
//
//        String userId = authentication.getName();
//        String password = (String) authentication.getCredentials();
//
//        UserPrincipal user = userService.loadUserById(userId);
//
//        if (user == null) {
//
//            return null;
//
//        } else {
//            if(forgotPassword.equals(BaseState.N)){
//                if (!password.equals(PasswordUtil.decrypt(user.getPassword(),secret))) {
//
//                    return null;
//
//                } else {
//
//                    Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
//
//                    return new UsernamePasswordAuthenticationToken(user, password, authorities);
//                }
//
//            }else {
//                if (!password.equals(user.getPassword())) {
//
//                    return null;
//
//                } else {
//
//                    Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
//
//                    return new UsernamePasswordAuthenticationToken(user, null, authorities);
//
//                }
//
//            }
//
//        }
//    }

    public boolean supports(Class<?> arg0) {
        return true;
    }


}

