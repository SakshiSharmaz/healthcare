package com.healthcare.service.impl;

import com.healthcare.config.CustomAuthenticationProvider;
import com.healthcare.config.JwtTokenProvider;
import com.healthcare.constants.ApiConstants;
import com.healthcare.model.User;
import com.healthcare.repository.UserRepository;
import com.healthcare.service.UserService;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    final UserRepository repository;

    private final JwtTokenProvider tokenProvider;

    private final CustomAuthenticationProvider authenticationManager;

    public UserServiceImpl(UserRepository repository, JwtTokenProvider tokenProvider, CustomAuthenticationProvider authenticationManager) {
        this.repository = repository;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public ResponseEntity<?> createHealthFacilityAdmin(User user) {
        user.setCreatedAt(System.currentTimeMillis());
        user.setUpdatedAt(System.currentTimeMillis());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String hashedPass1 = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashedPass1);
        repository.save(user);
        return ResponseEntity.ok("User created successfully");
    }

    @Override
    public ResponseEntity<?> login(User user) {
        JSONObject response = new JSONObject();
        HttpHeaders responseHeaders = new HttpHeaders();
        if (user.getPassword().trim().equals("") || (user.getEmail().trim().equals("") && user.getPhone().trim().equals(""))) {
            response.put(ApiConstants.MESSAGE, "Invalid User ID or password");
            return new ResponseEntity<>(response.toString(), responseHeaders, HttpStatus.UNAUTHORIZED);

        }

        User dtUser;


        if (user.getEmail() != null) {
            //login using email
            dtUser = repository.findFirstByEmail(user.getEmail());

        } else {
            //login using phone
            dtUser = repository.findFirstByPhone(user.getPhone());

        }

        if (dtUser != null) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dtUser.getUuid(), user.getPassword()));

            if (authentication != null) {
                String jwt = tokenProvider.generateToken(authentication);
                responseHeaders.set(ApiConstants.AUTHORIZATION, ApiConstants.BEARER + jwt);
                response.put(ApiConstants.MESSAGE, ApiConstants.LOGIN_SUCCESSFUL);
                return new ResponseEntity<>(dtUser, responseHeaders, HttpStatus.OK);
            }
        }

        response.put(ApiConstants.MESSAGE, "Invalid User ID or password");
        return new ResponseEntity<>(response.toString(), responseHeaders, HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<?> getUserList(String userType, String healthFacilityId) {
        return ResponseEntity.ok(repository.findByUserType(userType, UUID.fromString(healthFacilityId)));
    }
}
