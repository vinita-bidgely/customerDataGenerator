package com.bidgely.customerDataGenerator.controller;

import com.bidgely.customerDataGenerator.dto.RecreateProdUserDataRequest;
import com.bidgely.customerDataGenerator.dto.RecreateProdUserDataResponse;
import com.bidgely.customerDataGenerator.service.RecreateProdUserDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/recreate-prod-user-data")
@CrossOrigin(origins = "*")
public class RecreateProdUserDataController {
    
    private static final Logger logger = LoggerFactory.getLogger(RecreateProdUserDataController.class);
    
    @Autowired
    private RecreateProdUserDataService recreateProdUserDataService;
    
    @PostMapping("/process")
    public ResponseEntity<RecreateProdUserDataResponse> processRecreateProdUserData(
            @Valid @RequestBody RecreateProdUserDataRequest request) {
        
        logger.info("Received request to process RecreateProdUserData");
        
        try {
            RecreateProdUserDataResponse response = recreateProdUserDataService.processRecreateProdUserData(request);
            
            if (response.isSuccess()) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body(response);
            }
            
        } catch (Exception e) {
            logger.error("Error processing RecreateProdUserData request: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError()
                .body(RecreateProdUserDataResponse.error("Internal server error: " + e.getMessage()));
        }
    }
    
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("RecreateProdUserData API is running");
    }
} 