@api_Automation @quoteService @HO3
Feature: API Automation 
	  
 @PostCall_HO3 @HO3_quoteRequest_validDetails
  Scenario: Validate whether insurer and coinsurer details are inserted into quote-service database
  Given insurer and coinsurer details in HO3Flow
  And a valid token in HO3 flow
  When I send a post request with valid insurer and coinsurer details
  Then I validate the response of the post call in HO3 flow
  |statuscode  |success  |
  |200         |true     |
  
  @PostCall_HO3 @HO3_quoteRequest_Invalid_Token
  Scenario: Validate the response of quote service HO3 flow with invalid token
  Given insurer and coinsurer details in HO3Flow
  And an invalid token id in HO3 flow
  When I send a post request with valid insurer and coinsurer details with invalid token
  Then I validate the response of the post call in HO3 flow with invalid token
  |statuscode  |
  |401         |
  
  @PostCall_HO3 @HO3_quoteRequest_Invalid_Firstname
  Scenario: Validate the response of quote service HO3 flow with invalid firstname
  Given insurer details with invalid firstname in HO3Flow
  And a valid token in HO3 flow
  When I send a post request with invalid firstname of insurer
  Then I validate the response of the post call in HO3 flow with invalid firstname
  |statuscode  | success |
  |400         | false   |
  
  @PostCall_HO3 @HO3_quoteRequest_Invalid_DOB
  Scenario: Validate the response of quote service HO3 flow with invalid DOB
  Given insurer details with invalid DOB in HO3Flow
  And a valid token in HO3 flow
  When I send a post request with invalid DOB of insurer
  Then I validate the response of the post call in HO3 flow with invalid DOB
  |statuscode  | success |
  |400         | false   |
  
  @PostCall_HO3 @HO3_quoteRequest_Invalid_LeadID
  Scenario: Validate the response of quote service HO3 flow with invalid lead id
  Given insurer details with invalid lead id in HO3Flow
  And a valid token in HO3 flow
  When I send a post request with invalid lead id
  Then I validate the response of the post call in HO3 flow with invalid lead id
  |statuscode  | success |
  |400         | false   |
  
  @PostCall_HO3 @HO3_quoteRequest_Invalid_Address_StreetName
  Scenario: Validate the response of quote service HO3 flow with invalid streetname
  Given insurer details with invalid streetname in HO3Flow
  And a valid token in HO3 flow
  When I send a post request with invalid streetname of insurer
  Then I validate the response of the post call in HO3 flow with invalid streetname
  |statuscode  | success |
  |400         | false   |
  
  @PostCall_HO3 @HO3_quoteRequest_Invalid_Address_Zip
  Scenario: Validate the response of quote service HO3 flow with invalid zip
  Given insurer details with invalid zip in HO3Flow
  And a valid token in HO3 flow
  When I send a post request with invalid zip
  Then I validate the response of the post call in HO3 flow with invalid zip
  |statuscode  | success |
  |400         | false   |
  
  @PostCall_HO3 @HO3_quoteRequest_Invalid_Coinsurer_Firstname
  Scenario: Validate the response of quote service HO3 flow with invalid Coinsurer firstname
  Given insurer details with invalid Coinsurer firstname in HO3Flow
  And a valid token in HO3 flow
  When I send a post request with invalid Coinsurer firstname
  Then I validate the response of the post call in HO3 flow with invalid Coinsurer firstname
  |statuscode  | success |
  |400         | false   |
  
  @PostCall_HO3 @HO3_quoteRequest_Invalid_Coinsurer_DOB
  Scenario: Validate the response of quote service HO3 flow with invalid Coinsurer DOB
  Given insurer details with invalid Coinsurer DOB in HO3Flow
  And a valid token in HO3 flow
  When I send a post request with invalid Coinsurer DOB
  Then I validate the response of the post call in HO3 flow with invalid Coinsurer DOB
  |statuscode  | success |
  |400         | false   |
  
  @PostCall_HO3 @HO3_quoteRequest_Invalid_Address_Streetnumber
  Scenario: Validate the response of quote service HO3 flow with invalid streetnumber
  Given insurer details with invalid streetnumber in HO3Flow
  And a valid token in HO3 flow
  When I send a post request with invalid streetnumber of insurer
  Then I validate the response of the post call in HO3 flow with invalid streetnumber
  |statuscode  | success |
  |400         | false   |
  
  @PostCall_HO3 @HO3_quoteRequest_Invalid_Address_State
  Scenario: Validate the response of quote service HO3 flow with invalid state
  Given insurer details with invalid state in HO3Flow
  And a valid token in HO3 flow
  When I send a post request with invalid state of insurer
  Then I validate the response of the post call in HO3 flow with invalid state
  |statuscode  | success |
  |400         | false   |
  
  @PostCall_HO3 @HO3_quoteRequest_Invalid_Address_PurchaseDate
  Scenario: Validate the response of quote service HO3 flow with invalid purchase date
  Given insurer details with invalid purchase date in HO3Flow
  And a valid token in HO3 flow
  When I send a post request with invalid purchase date
  Then I validate the response of the post call in HO3 flow with invalid purchase date
  |statuscode  | success |
  |400         | false   |
  
  @PostCall_HO3 @HO3_quoteRequest_Invalid_Address_City
  Scenario: Validate the response of quote service HO3 flow with invalid city
  Given insurer details with invalid city in HO3Flow
  And a valid token in HO3 flow
  When I send a post request with invalid city
  Then I validate the response of the post call in HO3 flow with invalid city
  |statuscode  | success |
  |400         | false   |
  
  @PostCall_HO3 @HO3_quoteRequest_Invalid_Address_MaritalStatus
  Scenario: Validate the response of quote service HO3 flow with invalid marital status
  Given insurer details with invalid marital status in HO3Flow
  And a valid token in HO3 flow
  When I send a post request with invalid marital status
  Then I validate the response of the post call in HO3 flow with invalid marital status
  |statuscode  | success |
  |400         | false   |
  
  @PostCall_HO3 @HO3_quoteRequest_Invalid_PhoneNumber
  Scenario: Validate the response of quote service HO3 flow with invalid phone number
  Given insurer details with invalid phone number in HO3Flow
  And a valid token in HO3 flow
  When I send a post request with invalid phone number
  Then I validate the response of the post call in HO3 flow with invalid phone number
  |statuscode  | success |
  |400         | false   |