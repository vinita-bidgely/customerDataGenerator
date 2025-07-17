package com.bidgely.customerDataGenerator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RecreateProdUserDataResponse {
    
    @JsonProperty("success")
    private boolean success;
    
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("processedUsers")
    private int processedUsers;
    
    @JsonProperty("errorDetails")
    private String errorDetails;
    
    public RecreateProdUserDataResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    
    public RecreateProdUserDataResponse(boolean success, String message, int processedUsers) {
        this.success = success;
        this.message = message;
        this.processedUsers = processedUsers;
    }
    
    public static RecreateProdUserDataResponse success(String message, int processedUsers) {
        return new RecreateProdUserDataResponse(true, message, processedUsers);
    }
    
    public static RecreateProdUserDataResponse error(String message) {
        return new RecreateProdUserDataResponse(false, message);
    }
    
    public static RecreateProdUserDataResponse error(String message, String errorDetails) {
        RecreateProdUserDataResponse response = new RecreateProdUserDataResponse(false, message);
        response.setErrorDetails(errorDetails);
        return response;
    }
} 