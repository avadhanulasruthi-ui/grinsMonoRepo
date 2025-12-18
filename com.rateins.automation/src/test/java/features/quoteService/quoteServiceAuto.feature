@api_Automation @quoteService
Feature: API Automation 
	  
	@ValidateHealthCheckResponse
	Scenario: Validate the response of the quote service end point
  Given a health check end point URL
  When I send a GET request for the health check URL
  Then validate the response of the Health check endpoint with status code
  |body |statuscode  |
  |ok   |    200     |
  
  @ValidateHealthCheckResponse_InvalidURL
	Scenario: Validate the response of the quote service for invalid Health check end point
	Given an invalid health check end point URL
  When I send a GET request for the health check URL
  Then validate the response of the invalid Health check endpoint with status code
  |statuscode  |
  |    404     |
  
  @PostCall_Auto @Auto_quoteRequest_OneDriver_OneVehicle
  Scenario: Validate whether one driver and one vehicle details are inserted into quote-service database
  Given one driver and one vehicle details in AutoFlow
  And a valid token
  When I send a post request with one driver and one vehicle details
  Then I validate the response of the post call for One Driver and One vehicle in Auto flow
  |statuscode  |
  |200         |
  
  @PostCall_Auto @Auto_quoteRequest_ThreeDrivers_ThreeVehicles
  Scenario: Validate whether three drivers and three vehicle details are inserted into quote-service database
  Given three drivers and three vehicles in AutoFlow
  And a valid token
  When I send a post request with three driver and three vehicle details
  Then I validate the response of the post call for three Drivers and three vehicles in Auto flow
  |statuscode  |
  |200         |
  
  @PostCall_Auto @Auto_quoteRequest_FiveDrivers_FiveVehicles
  Scenario: Validate whether five drivers and five vehicle details are inserted into quote-service database
  Given five drivers and five vehicles in AutoFlow
  And a valid token
  When I send a post request with five driver and five vehicle details
  Then I validate the response of the post call for five Drivers and five vehicles in Auto flow
  |statuscode  |
  |200         |
  
  @PostCall_Auto @Auto_quoteRequest_FiveDrivers_FiveVehicles
  Scenario: Validate whether five drivers and five vehicle details are inserted into quote-service database
  Given five drivers and five vehicles in AutoFlow
  And a valid token
  When I send a post request with five driver and five vehicle details
  Then I validate the response of the post call for five Drivers and five vehicles in Auto flow
  |statuscode  |
  |200         |
  
  @PostCall_Auto @Auto_quoteRequest_OneDriverOneVehicle_Invalid_Token
  Scenario: Validate the response of quote service Auto flow with invalid token
  Given one driver and one vehicle details in AutoFlow
  And an invalid token id
  When I send a post request with one driver and one vehicle details with invalid token
  Then I validate the response of the post call for one driver and one vehicle with invalid token in Auto flow
  |statuscode  |
  |401         |
  
  @PostCall_Auto @Auto_quoteRequest_OneDriverOneVehicle_Invalid_FirstName
  Scenario: Validate the response of quote service Auto flow with invalid firstname
  Given one driver with invalid firstname and one vehicle in AutoFlow
  And a valid token
  When I send a post request with one driver and one vehicle details with invalid firstname
  Then I validate the response of the post call for one driver and one vehicle with invalid firstname in Auto flow
  |statuscode  | success  |
  |400         | false    | 
  
  @PostCall_Auto @Auto_quoteRequest_OneDriverOneVehicle_Invalid_LastName
  Scenario: Validate the response of quote service Auto flow with invalid lastname
  Given one driver with invalid lastname and one vehicle in AutoFlow
  And a valid token
  When I send a post request with one driver and one vehicle details with invalid lastname
  Then I validate the response of the post call for one driver and one vehicle with invalid lastname in Auto flow
  |statuscode  | success  |
  |400         | false    | 
  
  @PostCall_Auto @Auto_quoteRequest_OneDriverOneVehicle_Invalid_DOB
  Scenario: Validate the response of quote service Auto flow with invalid DOB
  Given one driver with invalid DOB and one vehicle in AutoFlow
  And a valid token
  When I send a post request with one driver and one vehicle details with invalid DOB
  Then I validate the response of the post call for one driver and one vehicle with invalid DOB in Auto flow
  |statuscode  | success  |
  |400         | false    | 
  
  @PostCall_Auto @Auto_quoteRequest_FiveDriversOneVehicle_Invalid_FirstName
  Scenario: Validate the response of quote service Auto flow with invalid firstname for five drivers
  Given five drivers with invalid firstname and one vehicle in AutoFlow
  And a valid token
  When I send a post request with five drivers and one vehicle details with invalid firstname
  Then I validate the response of the post call for five drivers and one vehicle with invalid firstname in AutoFlow
  |statuscode  | success  |
  |400         | false    |
  
  @PostCall_Auto @Auto_quoteRequest_FiveDriversOneVehicle_Invalid_DOB
  Scenario: Validate the response of quote service Auto flow with invalid DOB for five drivers
  Given five drivers with invalid DOB and one vehicle in AutoFlow
  And a valid token
  When I send a post request with five drivers and one vehicle details with invalid DOB
  Then I validate the response of the post call for five drivers and one vehicle with invalid DOB in AutoFlow
  |statuscode  | success  |
  |400         | false    |
  
  @PostCall_Auto @Auto_quoteRequest_OneDriverOneVehicle_Invalid_Miles
  Scenario: Validate the response of quote service Auto flow with invalid Miles
  Given one driver and one vehicle with invalid miles in AutoFlow
  And a valid token
  When I send a post request with one driver and one vehicle details with invalid miles
  Then I validate the response of the post call for one driver and one vehicle with invalid miles in AutoFlow
  |statuscode  | success  |
  |400         | false    | 
  
  @PostCall_Auto @Auto_quoteRequest_FiveDriversFiveVehicles_Invalid_Miles
  Scenario: Validate the response of quote service Auto flow for five drivers and five vehicles with invalid Miles
  Given five drivers and five vehicles with invalid miles in AutoFlow
  And a valid token
  When I send a post request with five drivers and five vehicles details with invalid miles
  Then I validate the response of the post call for five drivers and five vehicles with invalid miles in AutoFlow
  |statuscode  | success  |
  |400         | false    | 
  
  @PostCall_Auto @Auto_quoteRequest_OneDriverOneVehicle_Invalid_Ownership
  Scenario: Validate the response of quote service Auto flow with Invalid Ownership
  Given one driver and one vehicle with invalid ownership in AutoFlow
  And a valid token
  When I send a post request with one driver and one vehicle details with invalid ownership
  Then I validate the response of the post call for one driver and one vehicle with invalid ownership in AutoFlow
  |statuscode  | success  |
  |400         | false    |
  
  @PostCall_Auto @Auto_quoteRequest_FiveDriversFiveVehicles_Invalid_Ownership
  Scenario: Validate the response of quote service Auto flow for five drivers and five vehicles with Invalid Ownership
  Given five drivers and five vehicles with invalid ownership in AutoFlow
  And a valid token
  When I send a post request with five drivers and five vehicles with invalid ownership
  Then I validate the response of the post call for five drivers and five vehicles with invalid ownership in AutoFlow
  |statuscode  | success  |
  |400         | false    |
  
  
  @PostCall_Auto @Auto_quoteRequest_OneDriverOneVehicle_Invalid_MaritalStatus
  Scenario: Validate the response of quote service Auto flow with Invalid marital status
  Given one driver and one vehicle with invalid marital status in AutoFlow
  And a valid token
  When I send a post request with one driver and one vehicle details with invalid marital status
  Then I validate the response of the post call for one driver and one vehicle with invalid marital status in AutoFlow
  |statuscode  | success  |
  |400         | false    |
  
  @PostCall_Auto @Auto_quoteRequest_OneDriverOneVehicle_Invalid_PhoneNumber
  Scenario: Validate the response of quote service Auto flow with Invalid phone number
  Given one driver and one vehicle with invalid phone number in AutoFlow
  And a valid token
  When I send a post request with one driver and one vehicle details with invalid phone number
  Then I validate the response of the post call for one driver and one vehicle with invalid phone number in AutoFlow
  |statuscode  | success  |
  |400         | false    |
  
  @PostCall_Auto @Auto_quoteRequest_OneDriverOneVehicle_Invalid_Zip
  Scenario: Validate the response of quote service Auto flow with Invalid zip
  Given one driver and one vehicle with invalid zip in AutoFlow
  And a valid token
  When I send a post request with one driver and one vehicle details with invalid zip
  Then I validate the response of the post call for one driver and one vehicle with invalid zip in AutoFlow
  |statuscode  | success  |
  |400         | false    |
  
  @PostCall_Auto @Auto_quoteRequest_OneDriverOneVehicle_Invalid_Description
  Scenario: Validate the response of quote service Auto flow with Invalid description
  Given one driver and one vehicle with invalid description in AutoFlow
  And a valid token
  When I send a post request with one driver and one vehicle details with invalid description
  Then I validate the response of the post call for one driver and one vehicle with invalid description in AutoFlow
  |statuscode  | success  |
  |400         | false    |
  
  @PostCall_Auto @Auto_quoteRequest_OneDriverOneVehicle_Invalid_Vin
  Scenario: Validate the response of quote service Auto flow with Invalid vin
  Given one driver and one vehicle with invalid vin in AutoFlow
  And a valid token
  When I send a post request with one driver and one vehicle details with invalid vin
  Then I validate the response of the post call for one driver and one vehicle with invalid vin in AutoFlow
  |statuscode  | success  |
  |400         | false    |
  
    @PostCall_Auto @Auto_quoteRequest_OneDriverOneVehicle_Invalid_Usage
  Scenario: Validate the response of quote service Auto flow with Invalid usage
  Given one driver and one vehicle with invalid usage in AutoFlow
  And a valid token
  When I send a post request with one driver and one vehicle details with invalid usage
  Then I validate the response of the post call for one driver and one vehicle with invalid usage in AutoFlow
  |statuscode  | success  |
  |400         | false    |