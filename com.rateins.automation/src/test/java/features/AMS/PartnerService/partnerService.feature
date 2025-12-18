@api_Automation    @AMS    @partnerService
Feature: AMS Partner Services API Automation 
	  
	@GETCall
	Scenario: Validate the response of the GET request for partner from the partner database with partner id
  Given a valid partner id
	|partnerid                            |
	|78e83c0b-6c9b-4677-8a42-6c415577a660 |
  And a valid authentication bearer token
  When I send a GET request to fetch the partner details with valid partner id
  Then validate the response of the GET request for partner details with valid partner id
  |statuscode  |
  |200         |
	  
 	@GETCall     @InvalidToken
	Scenario: Validate the response of the GET request for partner from the partner database with invalid token
  Given a valid partner id
	|partnerid                            |
	|78e83c0b-6c9b-4677-8a42-6c415577a660 |
  And a invalid authentication bearer token
  When I send a GET request to fetch the partner details with invalid token
  Then validate the response of the GET request for partner details with invalid token
  |statuscode  |success   |
  |401         |false     |
  
 	@GETCall     @InvalidPartnerId
	Scenario: Validate the response of the GET request for partner from the partner database with invalid partner id
  Given an invalid partner id
	|account_name     |
	|Buster Bluth     |
  And a valid authentication bearer token
  When I send a GET request to fetch the partner details with invalid partner id
  Then validate the response of the GET request for partner details with invalid partner id
  |statuscode  |success   |
  |400         |false     |  
  
	@GETCall
	Scenario: Validate the response of the GET request for partner from the partner database with API key
  Given a valid api key
	|apikey                               |
	|2b682322-3193-4596-ad09-958ff62949cc |
  And a valid authentication bearer token
  When I send a GET request to fetch the partner details with valid api key
  Then validate the response of the GET request for partner details with valid api key
  |statuscode  |
  |200         |  
