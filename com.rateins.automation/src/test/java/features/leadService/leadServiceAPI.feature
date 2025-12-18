@api_Automation    @leadService
Feature: API Automation 
	  
	@ValidateResponse
	Scenario: Validate the response of the lead service end point with valid lead id
  Given a lead
	|id                                   |
	|dfee1395-5860-40e3-8149-2ef26662bd8e |
  And a token
  When I send a request to the URL
  Then validate the response of the endpoint with status code
  |id                                   |source                |externalId   |message          |statuscode  |
  |dfee1395-5860-40e3-8149-2ef26662bd8e |Online Marketplace    |N/A          |Lead was found   |200         |
	  
  @PostCall
  Scenario: Validate whether the user details are inserted into lead-service database
  Given the user details
  |firstname  |lastname  |source     |externalId  |recordtypename      |
  |Han        |Solo      |gri        |123456789   |Residential Leads   |
  And a token
  When I send a post request with the user details
  Then I validate the response of the post call
  |matchObjectType  |matchAction   |message                                                                       |statuscode  |
  |grinsLead        |created       |Salesforce lead created successfully. lead-service lead uploaded successfully.|201         |
  
  @PatchCall
  Scenario: Validate whether the user details are updated into lead-service database
  Given a valid lead id
	|id                                   |statuscode  |
	|dfee1395-5860-40e3-8149-2ef26662bd8e |200         |
  And a token
  When I send a patch request with the lead details
  Then I validate the response of the patch call
  |id                                   |success   |message                                                                      |statuscode  |
  |dfee1395-5860-40e3-8149-2ef26662bd8e |true      |Salesforce lead updated successfully. lead-service lead updated successfully.|200         |
  
  @PostCall    @PostCallDriverObject
  Scenario: Validate whether the driver object details are inserted into lead-service database
  Given the driver details
  |firstname  |lastname  |dateofbirth  |gender    |
  |John       |Hopkins   |10-07-1983   |Male      |
  And a token
  When I send a post request with the driver details
  Then I validate the response of the post call for driver object
  |message                                          |statuscode  |success   |
  |Driver Data successfully entered into Salesforce.|200         |true      |
  
  @PostCall    @PostCallVehicleObject
  Scenario: Validate whether the vehicle object details are inserted into lead-service database
  Given the vehicle details
  |year  |make  |model     |sub_model_trim                                         |vin                |vehicle_ownership |vehicle_usage |annual_mileage |
  |2020  |Ford  |Fusion SE |Sedan 4 AWD 2.0L L4 DOHC 16V Gas Both Automated Manual |3FA6P0T92L1111111  |Owned             |To/From Work  |9000           |
  And a token
  When I send a post request with the vehicle details
  Then I validate the response of the post call for vehicle object
  |message                                      |statuscode  |success   |
  |Vehicle successfully entered into Salesforce.|200         |true      |
  
 	@GetCall    @GetLeadData 
	Scenario: Validate the lead data from the lead service database
  Given a valid grins lead id
	|id                                   |
	|405344b4-f74e-4adb-8bac-999aa01bec2e |
  And a token
  When I send a get request to the lead service database
  Then I validate the status response code
	|statuscode  |
	|200         |
  
  @GetCall    @GetLeadData     @InvalidToken
	Scenario: Validate the lead data from the lead service database with Invalid Token
  Given a valid grins lead id
	|id                                   |
	|405344b4-f74e-4adb-8bac-999aa01bec2e |
  And an invalid token for the lead service
  When I send a get request to the lead service database with invalid token
  Then I validate the status response code with invalid token
  |statuscode  |success   |
  |401         |false     |
  
  @InvalidStatusCode
  Scenario: Validate the response with invalid lead id
  Given a lead
	|id                                   |
	|00ffaf0e-df2b-4ddc-bf3b-185aa52cff26 |
  And a token
  When I send a request to the URL
  Then I validate the status response code
	|statuscode  |
	|404         |
	
	@InvalidToken
  Scenario: Validate the response with invalid token
  Given a lead
	|id                                   |
	|00ffaf0e-df2b-4ddc-bf3b-185aa52cff26 |
  And an invalid token
  When I send a request to the URL
  Then I validate the response with invalid token
	|statuscode  |success   |
	|401         |false     |
	
	@PostCall     @PostCallErrorScenario
  Scenario: Validate when invalid user details are inserted into lead-service database
  Given the user details
  |firstname  |lastname  |source     |externalId  |recordtypename      |
  |Han        |          |gri        |123456789   |Residential Leads   |
  And a token
  When I send a post request with invalid user details
  Then validate the response of the post call with invalid user details
  |statuscode  |success   |
  |400         |false     |
  
  @PostCall     @PostCallErrorScenario
  Scenario: Validate when invalid firstname user details are inserted into lead-service database
  Given the invalid first name of the user
  |firstname  |lastname  |source     |externalId  |recordtypename      |
  |           |Solo      |gri        |123456789   |Residential Leads   |
  And a token
  When I send a post request with invalid user details
  Then validate the response of the post call with invalid user details
  |statuscode  |success   |
  |400         |false     |
  
  @PostCall     @PostCallErrorScenario     @InvalidToken
  Scenario: Validate whether user details are inserted into lead-service database with Invalid Token
  Given the user details
  |firstname  |lastname  |source     |externalId  |recordtypename      |
  |Han        |Solo      |gri        |123456789   |Residential Leads   |
  And an invalid token
  When I send a post request with invalid token details
  Then validate the response of the post call with invalid token details
  |statuscode  |success   |
  |401         |false     |
  
  @PostCall     @PostCallErrorScenario     @InvalidSource
  Scenario: Validate the Unsuccessful response when blank source is provided
  Given a blank source
  |firstname  |lastname  |source     |externalId  |recordtypename      |
  |Han        |Solo      |           |123456789   |Residential Leads   |
  And a token
  When I send a post request with invalid source details
  Then validate the response of the post call with invalid source details
  |statuscode  |success   |
  |400         |false     |
  
  @PostCall     @PostCallErrorScenario     @InvalidExternalId
  Scenario: Validate when invalid externalId user details are inserted into lead-service database
  Given the invalid external id
  |firstname  |lastname  |source     |externalId  |recordtypename      |
  |Han        |Solo      |gri        |            |Residential Leads   |
  And a token
  When I send a post request with invalid external id
  Then validate the response of the post call with invalid external id
  |statuscode  |success   |
  |400         |false     |
  
  @PostCall     @PostCallErrorScenario     @InvalidRecordTypeName
  Scenario: Validate when invalid recordtypename user details are inserted into lead-service database
  Given the invalid external id
  |firstname  |lastname  |source     |externalId  |recordtypename    |
  |Han        |Solo      |gri        |123456789   |                  |
  And a token
  When I send a post request with invalid recordtypename
  Then validate the response of the post call with invalid recordtypename
  |statuscode  |success   |
  |400         |false     |
  
  @PatchCall     @PatchCallErrorScenario
  Scenario: Validate when invalid lead details are updated into lead-service database
  Given a invalid lead id
	|id                            |statuscode  |
	|00ffaf0e-df2b-4ddc-bf3b-185aa |400         |
  And a token
  When I send a patch request with the invalid lead details
  Then validate the response of the patch call with invalid lead details
  |statuscode  |success   |
  |500         |false     |
  
  @PatchCall     @PatchCallErrorScenario     @InvalidToken
  Scenario: Validate whether user details are updated into lead-service database with Invalid Token
  Given a valid lead id
	|id                                   |statuscode  |
	|dfee1395-5860-40e3-8149-2ef26662bd8e |200         |
  And an invalid token
  When I send a patch request with the invalid token details
  Then validate the response of the patch call with invalid token details
  |statuscode  |success   |
  |401         |false     |
    
  @PostCall    @PostCallDriverObjectErrorScenario
  Scenario: Validate when invalid driver object details are inserted into lead-service database.
  Given the driver details
  |firstname  |lastname  |dateofbirth  |gender    |
  |           |Hopkins   |10-07-1983   |Male      |
  And a token
  When I send a post request with the invalid driver details
  Then I validate the response of the post call for invalid driver object
  |statuscode  |success   |
  |400         |false     |
  
  @PostCall    @PostCallDriverObjectErrorScenario     @InvalidToken
  Scenario: Validate whether the driver object details are inserted into lead-service database with Invalid Token
  Given the driver details
  |firstname  |lastname  |dateofbirth  |gender    |
  |John       |Hopkins   |10-07-1983   |Male      |
  And an invalid token
  When I send a post request with all the valid driver details and invalid token
  |id                                   |
	|d23599b6-89be-4b32-8c9c-ac4d8e0b355c |
  Then I validate the response of the post call for driver object with invalid token
  |statuscode  |success   |
  |401         |false     |
  
  @PostCall    @PostCallDriverObjectErrorScenario     @InvalidLastname
  Scenario: Validate whether the driver object details are inserted into lead-service database with Invalid last name
  Given the driver details
  |firstname  |lastname  |dateofbirth  |gender    |
  |John       |          |10-07-1983   |Male      |
  And a token
  When I send a post request with all the valid driver details and invalid last name
  |id                                   |
	|d23599b6-89be-4b32-8c9c-ac4d8e0b355c |
  Then I validate the response of the post call for driver object with invalid last name
  |statuscode  |success   |
  |400         |false     |
  
  @PostCall    @PostCallDriverObjectErrorScenario     @InvalidDOB
  Scenario: Validate whether the driver object details are inserted into lead-service database with Invalid DOB
  Given the driver details
  |firstname  |lastname  |dateofbirth  |gender    |
  |John       |Hopkins   |             |Male      |
  And a token
  When I send a post request with all the valid driver details and invalid DOB
  |id                                   |
	|d23599b6-89be-4b32-8c9c-ac4d8e0b355c |
  Then I validate the response of the post call for driver object with invalid DOB
  |statuscode  |success   |
  |400         |false     |
  
  @PostCall    @PostCallDriverObjectErrorScenario     @InvalidGender
  Scenario: Validate whether the driver object details are inserted into lead-service database with Invalid Gender
  Given the driver details
  |firstname  |lastname  |dateofbirth  |gender    |
  |John       |Hopkins   |10-07-1983   |          |
  And a token
  When I send a post request with all the valid driver details and invalid Gender
  |id                                   |
	|d23599b6-89be-4b32-8c9c-ac4d8e0b355c |
  Then I validate the response of the post call for driver object with invalid Gender
  |statuscode  |success   |
  |400         |false     |
  
  @PostCall    @PostCallVehicleObjectErrorScenario
  Scenario: Validate when invalid vehicle object details are inserted into lead-service database
  Given the vehicle details
  |year  |make  |model     |sub_model_trim                                         |vin                |vehicle_ownership |vehicle_usage |annual_mileage |
  |2020  |Ford  |Fusion SE |Sedan 4 AWD 2.0L L4 DOHC 16V Gas Both Automated Manual |3FA6P0T92L1111111  |                  |To/From Work  |9000           |
  And a token
  When I send a post request with the invalid vehicle details
  Then I validate the response of the post call for invalid vehicle object
  |statuscode  |success   |
  |400         |false     |
  
  @PostCall    @PostCallVehicleObjectErrorScenario     @InvalidToken
  Scenario: Validate whether the vehicle object details are inserted into lead-service database with Invalid Token
  Given the vehicle details
  |year  |make  |model     |sub_model_trim                                         |vin                |vehicle_ownership |vehicle_usage |annual_mileage |
  |2020  |Ford  |Fusion SE |Sedan 4 AWD 2.0L L4 DOHC 16V Gas Both Automated Manual |3FA6P0T92L1111111  |Owned             |To/From Work  |9000           |
  And an invalid token
  When I send a post request with the vehicle details with invalid token
  |id                                   |
	|d23599b6-89be-4b32-8c9c-ac4d8e0b355c |
  Then I validate the response of the post call for vehicle object with invalid token
  |statuscode  |success   |
  |401         |false     |
  
  @PostCall    @PostCallVehicleObjectErrorScenario     @InvalidYear
  Scenario: Validate whether the vehicle object details are inserted into lead-service database with Invalid Year
  Given the vehicle details without year
  |make  |model     |sub_model_trim                                         |vin                |vehicle_ownership |vehicle_usage |annual_mileage |
  |Ford  |Fusion SE |Sedan 4 AWD 2.0L L4 DOHC 16V Gas Both Automated Manual |3FA6P0T92L1111111  |Owned             |To/From Work  |9000           |
  And a token
  When I send a post request with the vehicle details with invalid year
  |id                                   |
	|d23599b6-89be-4b32-8c9c-ac4d8e0b355c |
  Then I validate the response of the post call for vehicle object with invalid year
  |statuscode  |success   |
  |400         |false     |
  
  @PostCall    @PostCallVehicleObjectErrorScenario     @InvalidMake
  Scenario: Validate whether the vehicle object details are inserted into lead-service database with Invalid Make
  Given the vehicle details
  |year  |make  |model     |sub_model_trim                                         |vin                |vehicle_ownership |vehicle_usage |annual_mileage |
  |2020  |      |Fusion SE |Sedan 4 AWD 2.0L L4 DOHC 16V Gas Both Automated Manual |3FA6P0T92L1111111  |Owned             |To/From Work  |9000           |
  And a token
  When I send a post request with the vehicle details with invalid make
  |id                                   |
	|d23599b6-89be-4b32-8c9c-ac4d8e0b355c |
  Then I validate the response of the post call for vehicle object with invalid make
  |statuscode  |success   |
  |400         |false     |
  
  @PostCall    @PostCallVehicleObjectErrorScenario     @InvalidModel
  Scenario: Validate whether the vehicle object details are inserted into lead-service database with Invalid Model
  Given the vehicle details
  |year  |make  |model   |sub_model_trim                                         |vin                |vehicle_ownership |vehicle_usage |annual_mileage |
  |2020  |Ford  |        |Sedan 4 AWD 2.0L L4 DOHC 16V Gas Both Automated Manual |3FA6P0T92L1111111  |Owned             |To/From Work  |9000           |
  And a token
  When I send a post request with the vehicle details with invalid model
  |id                                   |
	|d23599b6-89be-4b32-8c9c-ac4d8e0b355c |
  Then I validate the response of the post call for vehicle object with invalid model
  |statuscode  |success   |
  |400         |false     |
  
  @PostCall    @PostCallVehicleObjectErrorScenario     @InvalidSubModel
  Scenario: Validate a Bad Request error is returned when a vehicle object is posted with invalid sub-model data
  Given the vehicle details
  |year  |make  |model     |sub_model_trim   |vin                |vehicle_ownership |vehicle_usage |annual_mileage |
  |2020  |Ford  |Fusion SE |                 |3FA6P0T92L1111111  |Owned             |To/From Work  |9000           |
  And a token
  When I send a post request with the vehicle details with invalid sub-model
  |id                                   |
	|d23599b6-89be-4b32-8c9c-ac4d8e0b355c |
  Then I validate the response of the post call for vehicle object with invalid sub-model
  |statuscode  |success   |
  |400         |false     |
  
  @PostCall    @PostCallVehicleObjectErrorScenario     @InvalidVin
  Scenario: Validate a Bad Request error is returned when a vehicle object is posted with invalid vin data
  Given the vehicle details
  |year  |make  |model     |sub_model_trim                                         |vin     |vehicle_ownership |vehicle_usage |annual_mileage |
  |2020  |Ford  |Fusion SE |Sedan 4 AWD 2.0L L4 DOHC 16V Gas Both Automated Manual |        |Owned             |To/From Work  |9000           |
  And a token
  When I send a post request with the vehicle details with invalid vin
  |id                                   |
	|d23599b6-89be-4b32-8c9c-ac4d8e0b355c |
  Then I validate the response of the post call for vehicle object with invalid vin
  |statuscode  |success   |
  |400         |false     |
  
  @PostCall    @PostCallVehicleObjectErrorScenario     @InvalidVehicleUsage
  Scenario: Validate a Bad Request error is returned when a vehicle object is posted with invalid vehicle usage data
  Given the vehicle details
  |year  |make  |model     |sub_model_trim                                         |vin                |vehicle_ownership |vehicle_usage |annual_mileage |
  |2020  |Ford  |Fusion SE |Sedan 4 AWD 2.0L L4 DOHC 16V Gas Both Automated Manual |3FA6P0T92L1111111  |Owned             |              |9000           |
  And a token
  When I send a post request with the vehicle details with invalid vehicle usage
  |id                                   |
	|d23599b6-89be-4b32-8c9c-ac4d8e0b355c |
  Then I validate the response of the post call for vehicle object with invalid vehicle usage
  |statuscode  |success   |
  |400         |false     |

  @PostCall    @PostCallVehicleObjectErrorScenario     @InvalidAnnualMileage
  Scenario: Validate a Bad Request error is returned when a vehicle object is posted with invalid Annual Mileage data
  Given the vehicle details
  |year  |make  |model     |sub_model_trim                                         |vin                |vehicle_ownership |vehicle_usage |annual_mileage |
  |2020  |Ford  |Fusion SE |Sedan 4 AWD 2.0L L4 DOHC 16V Gas Both Automated Manual |3FA6P0T92L1111111  |Owned             |To/From Work  |               |
  And a token
  When I send a post request with the vehicle details with invalid Annual Mileage
  |id                                   |
	|d23599b6-89be-4b32-8c9c-ac4d8e0b355c |
  Then I validate the response of the post call for vehicle object with invalid Annual Mileage
  |statuscode  |success   |
  |400         |false     |
  
  
  