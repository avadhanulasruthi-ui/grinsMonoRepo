@api_Automation @quoteStartAPI
Feature: API Automation 
  
@PostCall @quoteStartAPI_happyPath
Scenario: Validate the response of the Quote start API post call for valid data
Given a Quote start API end point URL
And a valid token for quotes start API
When I send a post request with valid loan ID valid agent ID and valid lead ID
Then I validate the response of the post call for valid loan ID valid agent ID and valid lead ID
|statuscode  |success  |
|200         |true     |

@PostCall @quoteStartAPI_invalidToken
Scenario: Validate the response of the Quote start API post call for invalid token
Given a Quote start API end point URL
And an invalid token for quotes start API
When I send a post request in Quote start API with invalid token
Then I validate the response of the post call in Quote start API with invalid token
|statuscode  |success  |
|401         |false    |

@PostCall @quoteStartAPI_invalidLoanID
Scenario: Validate the response of the Quote start API post call for invalid Loan ID
Given a Quote start API end point URL with invalid loan ID
And a valid token for quotes start API
When I send a post request in Quote start API with invalid loan ID
Then I validate the response of the post call in Quote start API with invalid loan ID
|statuscode  |success  |
|400         |false    |

@PostCall @quoteStartAPI_invalidAgentID
Scenario: Validate the response of the Quote start API post call for invalid Agent ID
Given a Quote start API end point URL with invalid agent ID
And a valid token for quotes start API
When I send a post request in Quote start API with invalid agent ID
Then I validate the response of the post call in Quote start API with invalid agent ID
|statuscode  |success  |
|400         |false    |

@PostCall @quoteStartAPI_invalidLeadID
Scenario: Validate the response of the Quote start API post call for invalid Lead ID
Given a Quote start API end point URL with invalid lead ID
And a valid token for quotes start API
When I send a post request in Quote start API with invalid lead ID
Then I validate the response of the post call in Quote start API with invalid lead ID
|statuscode  |success  |
|400         |false    |