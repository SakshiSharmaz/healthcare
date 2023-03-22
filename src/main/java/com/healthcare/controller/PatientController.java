package com.healthcare.controller;

import com.healthcare.Constants;
import com.healthcare.dto.PatientDTO;
import com.healthcare.model.Patient;
import com.healthcare.model.Prescription;
import com.healthcare.model.User;
import com.healthcare.service.PatientService;
import io.micrometer.core.lang.Nullable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping(value = Constants.REST_BASE_PATH +"/patient")
public class PatientController {

    PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @PostMapping(value = "/create")
    @Operation(summary = "create a new patient")
    @Transactional
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = ""),
            @ApiResponse(responseCode = "501", description = Constants.UNEXPECTED_ERROR),
            @ApiResponse(responseCode = "401", description = Constants.UNAUTHORIZED)})
    public ResponseEntity<?> createPatient(@RequestBody Patient patient) {

        return service.createPatient(patient);

    }


    @GetMapping
    @Operation(summary = "search patient by mobile or email")
    @Transactional
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = ""),
            @ApiResponse(responseCode = "501", description = Constants.UNEXPECTED_ERROR),
            @ApiResponse(responseCode = "401", description = Constants.UNAUTHORIZED)})
    public ResponseEntity<?> searchPatients(@RequestParam(required = false) String mobile, @RequestParam(required = false) String email ) {

        if(mobile == null && email == null){
            return  new ResponseEntity<>("Please provide atleast one parameter",HttpStatus.BAD_REQUEST);
        }

        return service.searchPatients(mobile, email);

    }

    @GetMapping("/list")
    @Operation(summary = "Patient list ")
    @Transactional
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = ""),
            @ApiResponse(responseCode = "501", description = Constants.UNEXPECTED_ERROR),
            @ApiResponse(responseCode = "401", description = Constants.UNAUTHORIZED)})
    public ResponseEntity<?> patientList( @RequestParam String healthFacilityId) {

        return service.patientList(healthFacilityId);

    }

    @PostMapping("/prescribe")
    @Operation(summary = "prescribe medicine and examinations to a patient")
    @Transactional
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = ""),
            @ApiResponse(responseCode = "501", description = Constants.UNEXPECTED_ERROR),
            @ApiResponse(responseCode = "401", description = Constants.UNAUTHORIZED)})
    public ResponseEntity<?> prescribe(@RequestBody Prescription prescription) {

              return service.prescribe(prescription);

    }

    @PostMapping("/prescribe/list")
    @Operation(summary = "get list of prescriptions for a patient ")
    @Transactional
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = ""),
            @ApiResponse(responseCode = "501", description = Constants.UNEXPECTED_ERROR),
            @ApiResponse(responseCode = "401", description = Constants.UNAUTHORIZED)})
    public ResponseEntity<?> prescriptionList(@RequestParam String patientId) {

        return service.prescriptionList(patientId);

    }




}
