@api_Automation @AMS @policyService
Feature: API Automation
		  
@Get_call @ValidateAMSPolicyServiceResponse
Scenario: Validate the response of the AMS policy service endpoint
Given a AMS policy service endpoint URL
When I send a GET request for AMS policy service endpoint URL
Then validate the response of the AMS policy service endpoint
| statuscode |
| 200        |

@Get_call @ValidateAMSPolicyService_HealthCheckURL_InvalidEndpoint
Scenario: Validate the response of the AMS policy service with invalid endpoint
Given a AMS policy service endpoint URL for invalid endpoint
When I send a GET request for AMS policy service endpoint URL for invalid endpoint
Then validate the response of the AMS policy service endpoint for invalid endpoint
| statuscode |
| 404        |

 @PostCall @AMSPolicyService_validData_ClientID
 Scenario: Validate the response of AMS policy service with valid data and valid client ID
 Given valid data and valid client id in AMS policy service
 And a valid token in AMS policy service flow
 When I send a post request with valid data and valid client id in AMS policy service
 Then I validate the response of the post call for valid data and valid client id in AMS policy service
 |statuscode  |success  |
 |200         |true     |
 
 @PostCall @AMSPolicyService_validData_LookupCode
 Scenario: Validate the response of AMS policy service with valid data and valid lookup code
 Given valid data and valid lookup code in AMS policy service
 And a valid token in AMS policy service flow
 When I send a post request with valid data and valid lookup code in AMS policy service
 Then I validate the response of the post call for valid data and valid lookup code in AMS policy service
 |statuscode  |success  |
 |200         |true     |
 
 @PostCall @AMSPolicyService_ValidData_InvalidToken
 Scenario: Validate the response of AMS policy service with valid data and invalid token
 Given valid data and valid client id in AMS policy service
 And an invalid token in AMS policy service
 When I send a post request with valid data and invalid token in AMS policy service
 Then I validate the response of the post call for valid data and invalid token in AMS policy service
 |statuscode  |success  |
 |401         |false    |
 
 @PostCall @AMSPolicyService_InvalidClientID
 Scenario: Validate the response of AMS policy service with invalid client id
 Given invalid client id in AMS policy service
 And a valid token in AMS policy service flow
 When I send a post request with invalid client id in AMS policy service
 Then I validate the response of the post call for invalid client id in AMS policy service
 |statuscode  |success  |
 |400         |false    |
 
 @PostCall @AMSPolicyService_InvalidLookupCode
 Scenario: Validate the response of AMS policy service with invalid lookup code
 Given invalid lookup code in AMS policy service
 And a valid token in AMS policy service flow
 When I send a post request with invalid lookup code in AMS policy service
 Then I validate the response of the post call for invalid lookup code in AMS policy service
 |statuscode  |success  |
 |400         |false    |
 
@PostCall @AMSPolicyService_InvalidAgreementID
Scenario: Validate the response of AMS policy service with invalid agreement id
Given invalid agreement id in AMS policy service
And a valid token in AMS policy service flow
When I send a post request with invalid agreement id in AMS policy service
Then I validate the response of the post call for invalid agreement id in AMS policy service
|statuscode  |success  |
|400         |false    |

@PostCall @AMSPolicyService_InvalidPolicyDescription
Scenario: Validate the response of AMS policy service with invalid policy description
Given invalid policy description in AMS policy service
And a valid token in AMS policy service flow
When I send a post request with invalid policy description in AMS policy service
Then I validate the response of the post call for invalid policy description in AMS policy service
|statuscode  |success  |
|400         |false    |

@PostCall @AMSPolicyService_InvalidEffectiveDate
Scenario: Validate the response of AMS policy service with invalid effective date
Given invalid effective date in AMS policy service
And a valid token in AMS policy service flow
When I send a post request with invalid effective date in AMS policy service
Then I validate the response of the post call for invalid effective date in AMS policy service
|statuscode  |success  |
|400         |false    |

@PostCall @AMSPolicyService_InvalidExpirationDate
Scenario: Validate the response of AMS policy service with invalid expiration date
Given invalid expiration date in AMS policy service
And a valid token in AMS policy service flow
When I send a post request with invalid expiration date in AMS policy service
Then I validate the response of the post call for invalid expiration date in AMS policy service
|statuscode  |success  |
|400         |false    |

@PostCall @AMSPolicyService_InvalidPolicyNumber
Scenario: Validate the response of AMS policy service with invalid policy number
Given invalid policy number in AMS policy service
And a valid token in AMS policy service flow
When I send a post request with invalid policy number in AMS policy service
Then I validate the response of the post call for invalid policy number in AMS policy service
|statuscode  |success  |
|400         |false    |

@PostCall @AMSPolicyService_InvalidPolicyTypeCode
Scenario: Validate the response of AMS policy service with invalid policy type code
Given invalid policy type code in AMS policy service
And a valid token in AMS policy service flow
When I send a post request with invalid policy type code in AMS policy service
Then I validate the response of the post call for invalid policy type code in AMS policy service
|statuscode  |success  |
|400         |false    |

@PostCall @AMSPolicyService_InvalidPolicySource
Scenario: Validate the response of AMS policy service with invalid policy source
Given invalid policy source in AMS policy service
And a valid token in AMS policy service flow
When I send a post request with invalid policy source in AMS policy service
Then I validate the response of the post call for invalid policy source in AMS policy service
|statuscode  |success  |
|400         |false    |

@PostCall @AMSPolicyService_InvalidEmployeeLookupCode
Scenario: Validate the response of AMS policy service with invalid employee lookup code
Given invalid employee lookup code in AMS policy service
And a valid token in AMS policy service flow
When I send a post request with invalid employee lookup code in AMS policy service
Then I validate the response of the post call for invalid employee lookup code in AMS policy service
|statuscode  |success  |
|400         |false    |

@PostCall @AMSPolicyService_InvalidLines
Scenario: Validate the response of AMS policy service with invalid lines
Given invalid lines in AMS policy service
And a valid token in AMS policy service flow
When I send a post request with invalid lines in AMS policy service
Then I validate the response of the post call for invalid lines in AMS policy service
|statuscode  |success  |
|400         |false    |

@PostCall @AMSPolicyService_InvalidIssuingLocationCode
Scenario: Validate the response of AMS policy service with invalid issuing location code
Given invalid issuing location code in AMS policy service
And a valid token in AMS policy service flow
When I send a post request with invalid issuing location code in AMS policy service
Then I validate the response of the post call for invalid issuing location code in AMS policy service
|statuscode  |success  |
|400         |false    |

@PostCall @AMSPolicyService_InvalidLineTypeCode
Scenario: Validate the response of AMS policy service with invalid line type code
Given invalid line type code in AMS policy service
And a valid token in AMS policy service flow
When I send a post request with invalid line type code in AMS policy service
Then I validate the response of the post call for invalid line type code in AMS policy service
|statuscode  |success  |
|400         |false    |

@PostCall @AMSPolicyService_InvalidProfitCenterCode
Scenario: Validate the response of AMS policy service with invalid profit center code
Given invalid profit center code in AMS policy service
And a valid token in AMS policy service flow
When I send a post request with invalid profit center code in AMS policy service
Then I validate the response of the post call for invalid profit center code in AMS policy service
|statuscode  |success  |
|400         |false    |

@PostCall @AMSPolicyService_InvalidStatusCode
Scenario: Validate the response of AMS policy service with invalid status code
Given invalid status code in AMS policy service
And a valid token in AMS policy service flow
When I send a post request with invalid status code in AMS policy service
Then I validate the response of the post call for invalid status code in AMS policy service
|statuscode  |success  |
|400         |false    |

@Get_call @ValidateAMSPolicyServiceResponse_GetCommissions_UsingLookUpCode
Scenario: Validate the response of the list of commissions using Lookup code in AMS Policy Service
Given a AMS policy service endpoint URL to get list of commissions
| lookupCode |
| GOLAL1     |
And a valid token in AMS policy service flow
When I send a GET request for AMS policy service endpoint URL to get list of commissions
Then validate the response of the AMS policy service endpoint to get list of commissions
| statuscode |
| 200        |

@Get_call @ValidateAMSPolicyServiceResponse_Commissions_InvalidToken
Scenario: Validate the response of the AMS policy service endpoint with invalid token
Given a AMS policy service endpoint URL for invalid token
| lookupCode |
| GOLAL1     |
And an invalid token in AMS policy service
When I send a GET request for AMS policy service endpoint URL for invalid token
Then validate the response of the AMS policy service endpoint for invalid token
| statuscode |  success |
| 401        |  false   |

@Get_call @ValidateAMSPolicyServiceResponse_GetCommissions_InvalidLookUpCode
Scenario: Validate the response of the list of commissions using invalid Lookup code in AMS Policy Service
Given a AMS policy service endpoint URL to get list of commissions with invalid lookup code
| lookupCode |
| LOOKUP     |
And a valid token in AMS policy service flow
When I send a GET request for AMS policy service endpoint URL to get list of commissions with invalid lookup code
Then validate the response of the AMS policy service endpoint to get list of commissions with invalid lookup code
| statuscode |
| 404        |

@Get_call @ValidateAMSPolicyServiceResponse_GetCommissions_InvalidEndPoint
Scenario: Validate the response of the list of commissions using invalid end point in AMS Policy Service
Given an invalid AMS policy service endpoint URL
| lookupCode |
| GOLAL1     |
And a valid token in AMS policy service flow
When I send a GET request for invalid AMS policy service endpoint URL
Then validate the response of the invalid AMS policy service endpoint URL
| statuscode |
| 404        |

@Get_call @ValidateAMSPolicyServiceResponse_GetPolicies_Account_ValidClientLookupCode
Scenario: Validate the response of the list of policies using Client Lookup code in AMS Policy Service
Given a AMS policy service endpoint URL to get list of policies using client lookup code
| clientLookupCode |
| BLUTHBU001       |
And a valid token in AMS policy service flow
When I send a GET request for AMS policy service endpoint URL to get list of policies using client lookup code
Then validate the response of the AMS policy service endpoint to get list of policies using client lookup code
| statuscode |
| 200        |

@Get_call @ValidateAMSPolicyServiceResponse_GetPolicies_Account_InvalidEndPoint
Scenario: Validate the response of the list of policies using invalid end point in AMS Policy Service
Given a AMS policy service endpoint URL to get list of policies using invalid end point
| clientLookupCode |
| BLUTHBU001       |
And a valid token in AMS policy service flow
When I send a GET request for AMS policy service endpoint URL to get list of policies using invalid end point
Then validate the response of the AMS policy service endpoint to get list of policies using invalid end point
| statuscode |
| 404        |