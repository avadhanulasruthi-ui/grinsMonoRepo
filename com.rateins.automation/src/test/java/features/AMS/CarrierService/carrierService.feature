@api_Automation @AMS @carrierService
Feature: API Automation
		  
@Get_call @ValidateAMSCarrierServiceResponse
Scenario: Validate the response of the AMS carrier service endpoint
Given a AMS carrier service endpoint URL
And a valid token in AMS carrier service
When I send a GET request for AMS carrier service endpoint URL
Then validate the response of the AMS carrier service endpoint
| statuscode |
| 200        |

@Get_call @ValidateAMSCarrierServiceResponse_InvalidToken
Scenario: Validate the response of the AMS carrier service endpoint with invalid token
Given a AMS carrier service endpoint URL for invalid token
And an invalid token in AMS carrier service
When I send a GET request for AMS carrier service endpoint URL for invalid token
Then validate the response of the AMS carrier service endpoint for invalid token
| statuscode |  success |
| 401        |  false   |

@Get_call @ValidateAMSCarrierServiceResponse_InvalidEndpoint
Scenario: Validate the response of the AMS carrier service with invalid endpoint
Given a AMS carrier service endpoint URL for invalid endpoint
And a valid token in AMS carrier service
When I send a GET request for AMS carrier service endpoint URL for invalid endpoint
Then validate the response of the AMS carrier service endpoint for invalid endpoint
| statuscode |
| 404        |