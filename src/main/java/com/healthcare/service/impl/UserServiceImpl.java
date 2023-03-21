package com.healthcare.service.impl;

import com.healthcare.config.CustomAuthenticationProvider;
import com.healthcare.config.JwtTokenProvider;
import com.healthcare.constants.ApiConstants;
import com.healthcare.dto.UserDto;
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
    public ResponseEntity<?> createNewUser(User user) {


        //check if a user already exists by same email phone or username
        Boolean exists = repository.existsByEmailOrUsernameOrPhone(user.getEmail(), user.getUsername(), user.getPhone());
        if (!exists) {
            user.setCreatedAt(System.currentTimeMillis());
            user.setUpdatedAt(System.currentTimeMillis());
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String hashedPass1 = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(hashedPass1);
            repository.save(user);
            return ResponseEntity.ok("User created successfully");
        } else {
            return ResponseEntity.internalServerError().body("User with same details already exists.");
        }
    }

    @Override
    public ResponseEntity<?> login(User user) {
        JSONObject response = new JSONObject();
        HttpHeaders responseHeaders = new HttpHeaders();
        if (user.getPassword().trim().equals("") ) {
            response.put(ApiConstants.MESSAGE, "Invalid User ID or password");
            return new ResponseEntity<>(response.toString(), responseHeaders, HttpStatus.UNAUTHORIZED);

        }

        UserDto dtUser;


        if (user.getEmail() != null) {
            //login using email
            dtUser = repository.findFirstByEmailForTLA(user.getEmail());
            if( dtUser!= null && !dtUser.getUserType().equals("TLA")){
                Object[] firstByPhone  = repository.findFirstByEmail(user.getEmail()).get(0);
                dtUser = new UserDto( UUID.fromString((String) firstByPhone[0]), (String) firstByPhone[1], (String) firstByPhone[2],  (String)firstByPhone[3], (String)firstByPhone[4],  (String)firstByPhone[6],UUID.fromString((String) firstByPhone[5]), (String)firstByPhone[7] );
            }

        } else {
            //login using phone
            dtUser = repository.findFirstByPhoneForTLA(user.getPhone());
            if( dtUser!= null && !dtUser.getUserType().equals("TLA")){
                Object[] firstByPhone = repository.findFirstByPhone(user.getPhone()).get(0);
                dtUser = new UserDto( UUID.fromString((String) firstByPhone[0]), (String) firstByPhone[1], (String) firstByPhone[2],  (String)firstByPhone[3], (String)firstByPhone[4],  (String)firstByPhone[6],UUID.fromString((String) firstByPhone[5]), (String)firstByPhone[7] );



            }
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
        if (healthFacilityId == null) {
            return ResponseEntity.ok(repository.findByUserType(userType));

        } else
            return ResponseEntity.ok(repository.findByUserTypeAndHealthFacilityId(userType, UUID.fromString(healthFacilityId)));
    }
}
