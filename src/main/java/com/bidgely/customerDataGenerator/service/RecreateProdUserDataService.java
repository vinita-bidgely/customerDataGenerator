package com.bidgely.customerDataGenerator.service;

import com.bidgely.customerDataGenerator.configs.RecreateProdUserDataConfig;
import com.bidgely.customerDataGenerator.customerdata.RecreateProdUserDataTool;
import com.bidgely.customerDataGenerator.dto.RecreateProdUserDataRequest;
import com.bidgely.customerDataGenerator.dto.RecreateProdUserDataResponse;
import com.bidgely.customerDataGenerator.exceptions.BidgelyExceptions;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecreateProdUserDataService {
    
    private static final Logger logger = LoggerFactory.getLogger(RecreateProdUserDataService.class);
    
    @Autowired
    private RecreateProdUserDataTool recreateProdUserDataTool;
    
    public RecreateProdUserDataResponse processRecreateProdUserData(RecreateProdUserDataRequest request) {
        try {
            logger.info("Starting RecreateProdUserData processing for request: {}", request);
            
            // Convert request to config
            RecreateProdUserDataConfig config = convertRequestToConfig(request);
            
            // Convert config to JSON string
            String configJson = new Gson().toJson(config);
            
            // Execute the tool
            recreateProdUserDataTool.execute(configJson);
            
            int processedUsers = request.getUsersList() != null ? request.getUsersList().size() : 0;
            
            logger.info("Successfully processed RecreateProdUserData for {} users", processedUsers);
            
            return RecreateProdUserDataResponse.success(
                "Successfully processed user data recreation", 
                processedUsers
            );
            
        } catch (BidgelyExceptions e) {
            logger.error("BidgelyException occurred during RecreateProdUserData processing: {}", e.getMessage(), e);
            return RecreateProdUserDataResponse.error("Failed to process user data recreation: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error occurred during RecreateProdUserData processing: {}", e.getMessage(), e);
            return RecreateProdUserDataResponse.error("Unexpected error occurred: " + e.getMessage(), e.toString());
        }
    }
    
    private RecreateProdUserDataConfig convertRequestToConfig(RecreateProdUserDataRequest request) {
        RecreateProdUserDataConfig config = new RecreateProdUserDataConfig();
        
        config.setProdBaseUrl(request.getProdBaseUrl());
        config.setProdAccessToken(request.getProdAccessToken());
        config.setHouseType(request.getHouseType());
        config.setSubPilot(request.getSubPilot());
        config.setInputMeterFuel(request.getInputMeterFuel());
        config.setIngestionBucket(request.getIngestionBucket());
        config.setFileUploadBucket(request.getFileUploadBucket());
        config.setConstructUserFile(request.isConstructUserFile());
        config.setConstructRawFile(request.isConstructRawFile());
        config.setConstructInvoiceFile(request.isConstructInvoiceFile());
        config.setBillCycleCode(request.getBillCycleCode());
        config.setUsersList(request.getUsersList());
        config.setTimeZone(request.getTimeZone());
        config.setDateFormat(request.getDateFormat());
        config.setT0(request.getT0());
        config.setT1(request.getT1());
        config.setSourcePilotName(request.getSourcePilotName());
        config.setSourcePilotId(request.getSourcePilotId());
        config.setDestinationPilotName(request.getDestinationPilotName());
        config.setDestinationPilotId(request.getDestinationPilotId());
        
        return config;
    }
} 