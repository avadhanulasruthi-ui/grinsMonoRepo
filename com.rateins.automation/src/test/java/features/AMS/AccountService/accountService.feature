@api_Automation    @AMS    @accountService
Feature: AMS Account Services API Automation 
	  
	@GETCall
	Scenario: Validate the response of the GET request for the Applied EPIC accounts
  Given a valid account name
	|account_name     |
	|Buster Bluth     |
  And a valid bearer token
  When I send a GET request to fetch the Applied EPIC accounts
  Then validate the response of the GET request for the Applied EPIC accounts
  |statuscode  |
  |200         |
	  
 	@GETCall     @InvalidToken
	Scenario: Validate the response of the GET request for the Applied EPIC accounts with invalid token
  Given a valid account name
	|account_name     |
	|Buster Bluth     |
  And a invalid bearer token
  When I send a GET request to fetch the Applied EPIC accounts with invalid token
  Then validate the response of the GET request for the Applied EPIC accounts with invalid token
  |statuscode  |success   |
  |401         |false     |
  
  @PostCall
  Scenario: Validate the response of the POST request for the Applied EPIC accounts creation
  Given the valid user details for EPIC account
  |account_name |account_source |city    |state  |street        |postal_code  |employee_lookup_code |first_name |last_name |phone      |
  |Buster Bluth |Origin Point   |Chicago |IL     |466 N Main St |60137        |BROER1               |Buster     |Bluth     |5555555555 |
  And a valid bearer token
  When I send a POST request to create the Applied EPIC accounts
  Then validate the response of the POST request for the Applied EPIC accounts creation  
  |statuscode  |
  |201         |   

  @PostCall     @InvalidToken
  Scenario: Validate the response of the POST request for the Applied EPIC accounts creation with invalid token
  Given valid user details for EPIC account with invalid token
  |account_name |account_source |city    |state  |street        |postal_code  |employee_lookup_code |first_name |last_name |phone      |
  |Buster Bluth |Origin Point   |Chicago |IL     |466 N Main St |60137        |BROER1               |Buster     |Bluth     |5555555555 |
  And a invalid bearer token
  When I send a POST request to create the Applied EPIC accounts with invalid token
  Then validate the response of the POST request for the Applied EPIC accounts creation with invalid token
  |statuscode  |success   |
  |401         |false     | 
  
  @PostCall     @InvalidAccountName
  Scenario: Validate the response of the POST request for the Applied EPIC accounts creation with invalid account name
  Given valid user details for EPIC account with invalid account name
  |account_source |city    |state  |street        |postal_code  |employee_lookup_code |first_name |last_name |phone      |
  |Origin Point   |Chicago |IL     |466 N Main St |60137        |BROER1               |Buster     |Bluth     |5555555555 |
  And a valid bearer token
  When I send a POST request to create the Applied EPIC accounts with invalid account name
  Then validate the response of the POST request for the Applied EPIC accounts creation with invalid account name
  |statuscode  |success   |
  |400         |false     | 
  
  @PostCall
  Scenario: Validate the response of the POST request for the Applied EPIC activities associated with the client_id
  Given the valid user details for EPIC activity details
  |activity_code |state  |owner_code |
  |ACNT          |WI     |BROER1     |
  And a valid bearer token
  When I send a POST request to create the Applied EPIC activities
  Then validate the response of the POST request for the Applied EPIC activities creation
  |statuscode  |
  |201         |   
  
  @PostCall     @InvalidToken
  Scenario: Validate the response of the POST request for the Applied EPIC activities associated with the client_id and invalid token
  Given the valid user details for EPIC activity details with invalid token
  |activity_code |state  |owner_code |
  |ACNT          |WI     |BROER1     |
  And a invalid bearer token
  When I send a POST request to create the Applied EPIC activities with invalid token
  Then validate the response of the POST request for the Applied EPIC activities creation with invalid token
  |statuscode  |success   |
  |401         |false     |  
  
  @PostCall     @InvalidActivityCode
  Scenario: Validate the response of the POST request for the Applied EPIC activities with invalid activity code
  Given the valid user details for EPIC activity details with invalid activity code
  |activity_code |state  |owner_code |
  |sampl         |WI     |BROER1     |
  And a valid bearer token
  When I send a POST request to create the Applied EPIC activities with invalid activity code
  Then validate the response of the POST request for the Applied EPIC activities creation with invalid activity code
  |statuscode  |success   |
  |500         |false     |
  
  @PostCall
  Scenario: Validate the response of the POST request for the Applied EPIC activities associated with the lookup_code
  Given the valid user details for EPIC activity details with lookup code
  |activity_code |state  |owner_code |
  |ACNT          |WI     |BROER1     |
  And a valid bearer token
  When I send a POST request to create the Applied EPIC activities with lookup code
  Then validate the response of the POST request for the Applied EPIC activities creation with lookup code
  |statuscode  |
  |201         |   
  
  @PostCall     @InvalidToken
  Scenario: Validate the response of the POST request for the Applied EPIC activities associated with the lookup_code and invalid token
  Given the valid user details for EPIC activity details with lookup code and invalid token
  |activity_code |state  |owner_code |
  |ACNT          |WI     |BROER1     |
  And a invalid bearer token
  When I send a POST request to create the Applied EPIC activities with lookup code invalid token
  Then validate the response of the POST request for the Applied EPIC activities creation with lookup code and invalid token
  |statuscode  |success   |
  |401         |false     |  
  
  @PostCall1     @NoActivityCode
  Scenario: Validate the response of the POST request for the Applied EPIC activities associated with the lookup_code and no activity code
  Given the valid user details for EPIC activity details with lookup code and no activity code
  |state  |owner_code |
  |WI     |BROER1     |
  And a valid bearer token
  When I send a POST request to create the Applied EPIC activities with lookup code and no activity code
  Then validate the response of the POST request for the Applied EPIC activities creation with lookup code and no activity code
  |statuscode  |success   |
  |400         |false     |  
  
  @PostCall     @InvalidState
  Scenario: Validate the response of the POST request for the Applied EPIC activities associated with the lookup_code and invalid state
  Given the valid user details for EPIC activity details with lookup code and invalid state
  |activity_code |state  |owner_code |
  |ACNT          |22     |BROER1     |
  And a valid bearer token
  When I send a POST request to create the Applied EPIC activities with lookup code and invalid state
  Then validate the response of the POST request for the Applied EPIC activities creation with lookup code and invalid state
  |statuscode  |success   |
  |400         |false     |
  
  @PostCall     @NoOwnerCode
  Scenario: Validate the response of the POST request for the Applied EPIC activities associated with the lookup_code and no owner code
  Given the valid user details for EPIC activity details with lookup code and no owner code
  |activity_code |state  |
  |ACNT          |WI     |
  And a valid bearer token
  When I send a POST request to create the Applied EPIC activities with lookup code and no owner code
  Then validate the response of the POST request for the Applied EPIC activities creation with lookup code and no owner code
  |statuscode  |success   |
  |400         |false     |
  
  @PostCall     @InvalidLookupCode
  Scenario: Validate the response of the POST request for the Applied EPIC activities associated with invalid lookup_code
  Given the valid user details for EPIC activity details with invalid lookup code
  |activity_code |state  |owner_code |
  |ACNT          |WI     |BROER1     |
  And a valid bearer token
  When I send a POST request to create the Applied EPIC activities with invalid lookup code
  Then validate the response of the POST request for the Applied EPIC activities creation with invalid lookup code
  |statuscode  |success   |
  |500         |false     |
  
  @PostCall     @InvalidState
  Scenario: Validate the response of the POST request for the Applied EPIC activities with invalid state
  Given the valid user details for EPIC activity details with invalid state
  |activity_code |state  |owner_code |
  |ACNT          |22     |BROER1     |
  And a valid bearer token
  When I send a POST request to create the Applied EPIC activities with invalid state
  Then validate the response of the POST request for the Applied EPIC activities creation with invalid state
  |statuscode  |success   |
  |400         |false     |
  
  @PostCall     @NoOwnerCode
  Scenario: Validate the response of the POST request for the Applied EPIC activities with no owner code
  Given the valid user details for EPIC activity details with no owner code
  |activity_code |state  |
  |ACNT          |WI     |
  And a valid bearer token
  When I send a POST request to create the Applied EPIC activities with no owner code
  Then validate the response of the POST request for the Applied EPIC activities creation with no owner code
  |statuscode  |success   |
  |400         |false     |
  
  @PostCall     @NoAccountSource
  Scenario: Validate the response of the POST request for the Applied EPIC accounts creation with no account source
  Given valid user details for EPIC account with no account source
  |account_name |city    |state  |street        |postal_code  |employee_lookup_code |first_name |last_name |phone      |
  |Buster Bluth |Chicago |IL     |466 N Main St |60137        |BROER1               |Buster     |Bluth     |5555555555 |
  And a valid bearer token
  When I send a POST request to create the Applied EPIC accounts with no account source
  Then validate the response of the POST request for the Applied EPIC accounts creation with no account source
  |statuscode  |success   |
  |400         |false     | 
  
  @PostCall     @NoCity
  Scenario: Validate the response of the POST request for the Applied EPIC accounts creation with no city
  Given valid user details for EPIC account with no city
  |account_name |account_source |state  |street        |postal_code  |employee_lookup_code |first_name |last_name |phone      |
  |Buster Bluth |Origin Point   |IL     |466 N Main St |60137        |BROER1               |Buster     |Bluth     |5555555555 |
  And a valid bearer token
  When I send a POST request to create the Applied EPIC accounts with no city
  Then validate the response of the POST request for the Applied EPIC accounts creation with no city
  |statuscode  |success   |
  |400         |false     | 
  
  @PostCall     @NoState
  Scenario: Validate the response of the POST request for the Applied EPIC accounts creation with no state
  Given valid user details for EPIC account with no state
  |account_name |account_source |city    |street        |postal_code  |employee_lookup_code |first_name |last_name |phone      |
  |Buster Bluth |Origin Point   |Chicago |466 N Main St |60137        |BROER1               |Buster     |Bluth     |5555555555 |
  And a valid bearer token
  When I send a POST request to create the Applied EPIC accounts with no state
  Then validate the response of the POST request for the Applied EPIC accounts creation with no state
  |statuscode  |success   |
  |400         |false     | 
  
  @PostCall     @NoStreet
  Scenario: Validate the response of the POST request for the Applied EPIC accounts creation with no street
  Given valid user details for EPIC account with no street
  |account_name |account_source |city    |state  |postal_code  |employee_lookup_code |first_name |last_name |phone      |
  |Buster Bluth |Origin Point   |Chicago |IL     |60137        |BROER1               |Buster     |Bluth     |5555555555 |
  And a valid bearer token
  When I send a POST request to create the Applied EPIC accounts with no street
  Then validate the response of the POST request for the Applied EPIC accounts creation with no street
  |statuscode  |success   |
  |400         |false     | 
  
  @PostCall     @NoPostalCode
  Scenario: Validate the response of the POST request for the Applied EPIC accounts creation with no postal code
  Given valid user details for EPIC account with no postal code
  |account_name |account_source |city    |state  |street         |employee_lookup_code |first_name |last_name |phone      |
  |Buster Bluth |Origin Point   |Chicago |IL     |466 N Main St  |BROER1               |Buster     |Bluth     |5555555555 |
  And a valid bearer token
  When I send a POST request to create the Applied EPIC accounts with no postal code
  Then validate the response of the POST request for the Applied EPIC accounts creation with no postal code
  |statuscode  |success   |
  |400         |false     |
  
  @PostCall     @NoEmployeeLookUpCode
  Scenario: Validate the response of the POST request for the Applied EPIC accounts creation with no employee lookup code
  Given valid user details for EPIC account with no employee lookup code
  |account_name |account_source |city    |state  |street         |postal_code |first_name |last_name |phone      |
  |Buster Bluth |Origin Point   |Chicago |IL     |466 N Main St  |60137       |Buster     |Bluth     |5555555555 |
  And a valid bearer token
  When I send a POST request to create the Applied EPIC accounts with no employee lookup code
  Then validate the response of the POST request for the Applied EPIC accounts creation with no employee lookup code
  |statuscode  |success   |
  |400         |false     |
  
  @PostCall     @NoFirstName
  Scenario: Validate the response of the POST request for the Applied EPIC accounts creation with no first name
  Given valid user details for EPIC account with no first name
  |account_name |account_source |city    |state  |street         |postal_code |employee_lookup_code |last_name |phone      |
  |Buster Bluth |Origin Point   |Chicago |IL     |466 N Main St  |60137       |BROER1               |Bluth     |5555555555 |
  And a valid bearer token
  When I send a POST request to create the Applied EPIC accounts with no first name
  Then validate the response of the POST request for the Applied EPIC accounts creation with no first name
  |statuscode  |success   |
  |400         |false     |
  
  @PostCall     @NoLastName
  Scenario: Validate the response of the POST request for the Applied EPIC accounts creation with no last name
  Given valid user details for EPIC account with no last name
  |account_name |account_source |city    |state  |street         |postal_code |employee_lookup_code |first_name |phone      |
  |Buster Bluth |Origin Point   |Chicago |IL     |466 N Main St  |60137       |BROER1               |Buster     |5555555555 |
  And a valid bearer token
  When I send a POST request to create the Applied EPIC accounts with no last name
  Then validate the response of the POST request for the Applied EPIC accounts creation with no last name
  |statuscode  |success   |
  |400         |false     |
  
  @PostCall     @NoPhoneNumber
  Scenario: Validate the response of the POST request for the Applied EPIC accounts creation with no phone number
  Given valid user details for EPIC account with no phone number
  |account_name |account_source |city    |state  |street         |postal_code |employee_lookup_code |first_name |last_name   |
  |Buster Bluth |Origin Point   |Chicago |IL     |466 N Main St  |60137       |BROER1               |Buster     |Bluth       |
  And a valid bearer token
  When I send a POST request to create the Applied EPIC accounts with no phone number
  Then validate the response of the POST request for the Applied EPIC accounts creation with no phone number
  |statuscode  |success   |
  |400         |false     |
  
  @GETCall     @NoAccountName
	Scenario: Validate the response of the GET request for the Applied EPIC accounts with no account name
  Given no account name
  And a valid bearer token
  When I send a GET request to fetch the Applied EPIC accounts with no account name
  Then validate the response of the GET request for the Applied EPIC accounts with no account name
  |statuscode  |success   |
  |400         |false     |
  
  @GETCall     @InvalidAccountName
	Scenario: Validate the response of the GET request for the Applied EPIC accounts with invalid account name
  Given an invalid account name
  |account_name     |
	|Invalid user     |
  And a valid bearer token
  When I send a GET request to fetch the Applied EPIC accounts with an invalid account name
  Then validate the response of the GET request for the Applied EPIC accounts with an invalid account name
  |statuscode  |
  |200         |
  
  