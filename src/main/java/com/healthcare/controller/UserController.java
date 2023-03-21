package com.healthcare.controller;

import com.healthcare.Constants;
import com.healthcare.model.User;
import com.healthcare.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping(value = Constants.REST_BASE_PATH + Constants.REST_USERS_PATH)
public class UserController {


    private final UserService userService;


    public UserController(UserService userService) {

        this.userService = userService;
    }


    @PostMapping
    @Operation(summary = "create a new user")
    @Transactional
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = ""),
            @ApiResponse(responseCode = "501", description = Constants.UNEXPECTED_ERROR),
            @ApiResponse(responseCode = "401", description = Constants.UNAUTHORIZED)})
    public ResponseEntity<?> createHealthFacilityAdmin(@RequestBody User user) {

        return userService.createHealthFacilityAdmin(user);

    }




}
