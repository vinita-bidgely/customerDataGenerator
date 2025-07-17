package com.bidgely.customerDataGenerator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class RecreateProdUserDataRequest {
    
    @JsonProperty("prodBaseUrl")
    private String prodBaseUrl;
    
    @JsonProperty("prodAccessToken")
    private String prodAccessToken;
    
    @JsonProperty("usersFileLocation")
    private String usersFileLocation;
    
    @JsonProperty("houseType")
    private String houseType;
    
    @JsonProperty("subPilot")
    private String subPilot;
    
    @JsonProperty("inputMeterFuel")
    private String inputMeterFuel;
    
    @JsonProperty("rawDataStructureFilePath")
    private String rawDataStructureFilePath;
    
    @JsonProperty("ingestionBucket")
    private String ingestionBucket;
    
    @JsonProperty("fileUploadBucket")
    private String fileUploadBucket;
    
    @JsonProperty("constructUserFile")
    private boolean constructUserFile;
    
    @JsonProperty("constructRawFile")
    private boolean constructRawFile;
    
    @JsonProperty("constructInvoiceFile")
    private boolean constructInvoiceFile;
    
    @JsonProperty("userPrefFile")
    private String userPrefFile;
    
    @JsonProperty("billCycleCode")
    private String billCycleCode;
    
    @JsonProperty("usersList")
    private List<String> usersList;
    
    @JsonProperty("timeZone")
    private String timeZone;
    
    @JsonProperty("dateFormat")
    private String dateFormat;
    
    @JsonProperty("t0")
    private String t0;
    
    @JsonProperty("t1")
    private String t1;
    
    @JsonProperty("sourcePilotName")
    private String sourcePilotName;
    
    @JsonProperty("sourcePilotId")
    private String sourcePilotId;
    
    @JsonProperty("destinationPilotName")
    private String destinationPilotName;
    
    @JsonProperty("destinationPilotId")
    private String destinationPilotId;
} 