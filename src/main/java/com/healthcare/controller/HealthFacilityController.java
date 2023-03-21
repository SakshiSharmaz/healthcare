package com.healthcare.controller;

import com.healthcare.Constants;
import com.healthcare.model.HealthFacility;
import com.healthcare.model.User;
import com.healthcare.service.FacilityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping(value = Constants.REST_BASE_PATH + "/facility")
public class HealthFacilityController {

    FacilityService service;

    public HealthFacilityController(FacilityService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "create a new Health Facility")
    @Transactional
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = ""),
            @ApiResponse(responseCode = "501", description = Constants.UNEXPECTED_ERROR),
            @ApiResponse(responseCode = "401", description = Constants.UNAUTHORIZED)})
    public ResponseEntity<?> createHealthFacility(@RequestBody HealthFacility facility) {

        System.out.println(facility == null);
//        System.out.println(facility.toString());
        return service.createHealthFacility(facility);

    }


    @GetMapping
    @Operation(summary = "Search for a health facility")
    @Transactional
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = ""),
            @ApiResponse(responseCode = "501", description = Constants.UNEXPECTED_ERROR),
            @ApiResponse(responseCode = "401", description = Constants.UNAUTHORIZED)})
    public ResponseEntity<?> searchHealthFacility(@RequestParam(required = false) Long pincode,@RequestParam(required = false) String state, @RequestParam(required = false) String district ) {


        return service.searchHealthFacility(pincode,state, district);

    }


}
