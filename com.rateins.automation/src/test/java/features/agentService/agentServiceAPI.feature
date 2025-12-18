@api_Automation    @agentService
Feature: API Automation 
	  
  @GetCall     @agentDetailsValidData
  Scenario: Validate whether the user details are updated into agent-service database
  Given a valid agent details
	|firstName  |lastName  |
	|Nick       |Brown     |
  And a valid authorization token
  When I send a get request with the agent details
  Then I validate the response of the get call
  |name      |success   |statuscode  |sfUserId            |
  |Nick Brown|true      |200         |0055C0000048iqsQAA  |
  
  @GetCall     @agentDetailsInValidToken
  Scenario: Validate whether the user details are fetched from agent-service database with Invalid Token
  Given a valid agent details
	|firstName  |lastName  |
	|Nick       |Brown     |
  And an invalid authorization token
  When I send a get request with the agent details with invalid token
  Then I validate the response of the get call with invalid token
  |statuscode  |success   |
  |401         |false     |
  
  @GetCall     @agentDetailsNoLastName
  Scenario: Validate whether agent-service fetches the user details from Salesforce with No Last Name
  Given a invalid agent details with no last name
	|firstName  |
	|Nick       |
  And a valid authorization token
  When I send a get request with the agent details with no last name
  Then I validate the response of the get call with no last name
  |statuscode  |
  |400         |
  
  @GetCall     @agentDetailsNoFirstName
  Scenario: Validate whether agent-service fetches the user details from Salesforce with No First Name
  Given a invalid agent details with no first name
	|lastName  |
	|Brown     |
  And a valid authorization token
  When I send a get request with the agent details with no first name
  Then I validate the response of the get call with no first name
  |statuscode  |
  |400         |
  
  @GetCall     @agentDetailsInvalidFirstName
  Scenario: Validate whether agent-service fetches the user details from Salesforce with Invalid First Name
  Given a invalid agent details with invalid first name
	|firstName  |lastName  |
	|Test       |Brown     |
  And a valid authorization token
  When I send a get request with the agent details with invalid first name
  Then I validate the response of the get call with invalid first name
  |statuscode  |success   |
  |500         |false     |
  
  @GetCall     @agentDetailsInvalidLastName
  Scenario: Validate whether agent-service fetches the user details from Salesforce with Invalid Last Name
  Given a invalid agent details with invalid last name
	|firstName  |lastName  |
	|Nick       |Sample    |
  And a valid authorization token
  When I send a get request with the agent details with invalid last name
  Then I validate the response of the get call with invalid last name
  |statuscode  |success   |
  |500         |false     |
  
  @GetCall     @agentDetailsInvalidFirstAndLastName
  Scenario: Validate whether agent-service fetches the user details from Salesforce with Invalid First and Last Name
  Given a invalid agent details with invalid first and last name
	|firstName  |lastName  |
	|Test       |Sample    |
  And a valid authorization token
  When I send a get request with the agent details with invalid first and last name
  Then I validate the response of the get call with invalid first and last name
  |statuscode  |success   |
  |500         |false     |
  
  @GetCall     @allagentDetailsValidData
  Scenario: Validate whether all the user details are fetched from Salesforce agent-service database
  Given a valid end point url
  When I send a get request to fetch all the agent details
  Then I validate the response of the get call whether all user details are fetched
  |statuscode  |
  |200         |  
  
  @GetCall     @allagentDetailsInValidKey
  Scenario: Validate whether all the user details are fetched from Salesforce agent-service database with invalid key
  Given a valid end point url
  When I send a get request to fetch all the agent details with invalid key
  Then I validate the response of the get call whether all user details are fetched with invalid key
  |statuscode  |
  |401         |  
  
  @GetCall     @allagentDetailsInValidEndPoint
  Scenario: Validate whether all the user details are fetched from Salesforce agent-service database with invalid endpoint
  Given a invalid end point url
  When I send a get request to fetch all the agent details with valid key
  Then I validate the response of the get call whether all user details are fetched with invalid endpoint
  |statuscode  |
  |404         |  
  
  
  