package com.healthcare.controller;

import com.healthcare.Constants;
import com.healthcare.model.Medicine;
import com.healthcare.model.User;
import com.healthcare.service.MedicineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.UUID;

@RestController
@RequestMapping(value = Constants.REST_BASE_PATH + "/medicine")
public class MedicineController {


    public MedicineController(MedicineService service) {
        this.service = service;
    }

    MedicineService service ;

    @PostMapping
    @Operation(summary = "create a new medicine")
    @Transactional
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = ""),
            @ApiResponse(responseCode = "501", description = Constants.UNEXPECTED_ERROR),
            @ApiResponse(responseCode = "401", description = Constants.UNAUTHORIZED)})
    public ResponseEntity<?> createMedicine(@RequestBody Medicine medicine) {

        return service.addNewMedicine(medicine);

    }

    @PutMapping
    @Operation(summary = "Update medicine stock")
    @Transactional
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = ""),
            @ApiResponse(responseCode = "501", description = Constants.UNEXPECTED_ERROR),
            @ApiResponse(responseCode = "401", description = Constants.UNAUTHORIZED)})
    public ResponseEntity<?> updateMedicineStock(@RequestParam UUID uuid, @RequestParam int count) {

        return service.updateMedicineCount(uuid, count);

    }

    @GetMapping
    @Operation(summary = "Search for medicines")
    @Transactional
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = ""),
            @ApiResponse(responseCode = "501", description = Constants.UNEXPECTED_ERROR),
            @ApiResponse(responseCode = "401", description = Constants.UNAUTHORIZED)})
    public ResponseEntity<?> searchMedicine(@RequestParam String paramValue) {

        return service.searchMedicine(paramValue);

    }

    @GetMapping(value = "/list")
    @Operation(summary = "List of all medicines")
    @Transactional
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = ""),
            @ApiResponse(responseCode = "501", description = Constants.UNEXPECTED_ERROR),
            @ApiResponse(responseCode = "401", description = Constants.UNAUTHORIZED)})
    public ResponseEntity<?> alMedicines() {

        return service.allMedicines();

    }



}
