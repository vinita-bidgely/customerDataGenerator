# RecreateProdUserData API Documentation

## Overview
The RecreateProdUserData API allows you to recreate production user data for testing purposes. This API requires bearer token authentication.

## Base URL
```
http://localhost:8080/api/v1/recreate-prod-user-data
```

## Authentication
All API endpoints require bearer token authentication. Include the token in the Authorization header:
```
Authorization: Bearer <your-token>
```

## Endpoints

### 1. Process RecreateProdUserData
**POST** `/process`

Recreates production user data based on the provided configuration.

#### Request Headers
```
Content-Type: application/json
Authorization: Bearer <your-token>
```

#### Request Body
```json
{
    "prodBaseUrl": "https://caapi.bidgely.com",
    "prodAccessToken": "20ffebe2-182f-40e3-9d82-f19c84486efb",
    "usersFileLocation": "/backend/nsp/recreateProdUser/prodUserUUIDList.txt",
    "houseType": "",
    "subPilot": "",
    "inputMeterFuel": "AMI-ELECTRIC",
    "rawDataStructureFilePath": "/backend/nsp/recreateProdUser/rawDataFileStructure.csv",
    "ingestionBucket": "",
    "fileUploadBucket": "bidgely-adhoc-productqa/ReCreatingProdData",
    "constructUserFile": true,
    "constructRawFile": true,
    "constructInvoiceFile": true,
    "userPrefFile": "/backend/nsp/Email/USER_PREFS.csv",
    "billCycleCode": "userBCC",
    "usersList": ["user-uuid-1", "user-uuid-2"],
    "timeZone": "America/Los_Angeles",
    "dateFormat": "yyyy-MM-dd",
    "t0": "1",
    "t1": "2042876338",
    "sourcePilotName": "nsp",
    "sourcePilotId": "123",
    "destinationPilotName": "nsp-test",
    "destinationPilotId": "456"
}
```

#### Request Parameters

| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| prodBaseUrl | String | Yes | Production API base URL |
| prodAccessToken | String | Yes | Production API access token |
| usersList | Array<String> | Yes | List of user UUIDs to process |
| inputMeterFuel | String | Yes | Meter fuel type (e.g., "AMI-ELECTRIC") |
| fileUploadBucket | String | Yes | S3 bucket for file uploads |
| constructUserFile | Boolean | No | Whether to construct user file (default: false) |
| constructRawFile | Boolean | No | Whether to construct raw file (default: false) |
| constructInvoiceFile | Boolean | No | Whether to construct invoice file (default: false) |
| billCycleCode | String | No | Bill cycle code |
| timeZone | String | No | Timezone for date formatting |
| dateFormat | String | No | Date format string |
| t0 | String | No | Start timestamp (default: "1") |
| t1 | String | No | End timestamp (default: "2042876338") |
| sourcePilotName | String | No | Source pilot name |
| sourcePilotId | String | No | Source pilot ID |
| destinationPilotName | String | No | Destination pilot name |
| destinationPilotId | String | No | Destination pilot ID |

#### Response

**Success Response (200 OK)**
```json
{
    "success": true,
    "message": "Successfully processed user data recreation",
    "processedUsers": 2,
    "errorDetails": null
}
```

**Error Response (400 Bad Request)**
```json
{
    "success": false,
    "message": "Failed to process user data recreation: Invalid configuration",
    "processedUsers": 0,
    "errorDetails": "Configuration validation failed"
}
```

**Error Response (500 Internal Server Error)**
```json
{
    "success": false,
    "message": "Internal server error: Unexpected error occurred",
    "processedUsers": 0,
    "errorDetails": "java.lang.Exception: Error details..."
}
```

### 2. Health Check
**GET** `/health`

Returns the health status of the API.

#### Response
```
RecreateProdUserData API is running
```

## Example Usage

### Using curl
```bash
curl -X POST \
  http://localhost:8080/api/v1/recreate-prod-user-data/process \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer your-token-here' \
  -d '{
    "prodBaseUrl": "https://caapi.bidgely.com",
    "prodAccessToken": "20ffebe2-182f-40e3-9d82-f19c84486efb",
    "usersList": ["user-uuid-1", "user-uuid-2"],
    "inputMeterFuel": "AMI-ELECTRIC",
    "fileUploadBucket": "bidgely-adhoc-productqa/ReCreatingProdData",
    "constructUserFile": true,
    "constructRawFile": true,
    "constructInvoiceFile": true,
    "billCycleCode": "userBCC"
  }'
```

### Using JavaScript/Fetch
```javascript
const response = await fetch('http://localhost:8080/api/v1/recreate-prod-user-data/process', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json',
    'Authorization': 'Bearer your-token-here'
  },
  body: JSON.stringify({
    prodBaseUrl: 'https://caapi.bidgely.com',
    prodAccessToken: '20ffebe2-182f-40e3-9d82-f19c84486efb',
    usersList: ['user-uuid-1', 'user-uuid-2'],
    inputMeterFuel: 'AMI-ELECTRIC',
    fileUploadBucket: 'bidgely-adhoc-productqa/ReCreatingProdData',
    constructUserFile: true,
    constructRawFile: true,
    constructInvoiceFile: true,
    billCycleCode: 'userBCC'
  })
});

const result = await response.json();
console.log(result);
```

## Error Handling

The API returns appropriate HTTP status codes:

- **200 OK**: Request processed successfully
- **400 Bad Request**: Invalid request parameters or validation errors
- **401 Unauthorized**: Missing or invalid bearer token
- **500 Internal Server Error**: Server-side errors

## Security

- All endpoints require bearer token authentication
- CORS is enabled for cross-origin requests
- CSRF protection is disabled for API endpoints
- Session management is stateless

## Notes

- The API processes users asynchronously and may take some time to complete
- Large user lists should be processed in batches
- Ensure proper AWS credentials are configured for S3 operations
- Monitor logs for detailed processing information 