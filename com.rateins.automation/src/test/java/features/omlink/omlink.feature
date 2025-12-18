@api_Automation @Omlink
Feature: API Automation
		  
@Get_call @ValidateOmlinkPrefillURLResponse
Scenario: Validate the response of the quote service endpoint
Given a omlink prefill endpoint URL
| loanID                               |  Company  |
| 1000daf8-9701-4d59-8b15-ca61dae32669 |  gri      |
And a valid token in Omlink
When I send a GET request for omlink prefill endpoint URL
Then validate the response of the omlink prefill endpoint with status code
| eligible | statuscode |
| true     | 200        |


#@Get_call @ValidateOmlinkPrefillURLResponse_IneligibleLoan
#Scenario: Validate the response of the quote service endpoint with ineligible loan
#Given a omlink prefill endpoint URL
#| loanID                               |  Company  |
#| fdd738ce-e0ae-4635-b943-0f641ef11085 |  gri      |
#And a valid token in Omlink
#When I send a GET request for omlink prefill endpoint URL
#Then validate the response of the omlink prefill endpoint with ineligible loan
#| success  | statuscode |
#| false    | 400        |

@Get_call @ValidateOmlinkPrefillURLResponse_InvalidToken
Scenario: Validate the response of the quote service endpoint with invalid token
Given a omlink prefill endpoint URL
| loanID                               |  Company  |
| 1000daf8-9701-4d59-8b15-ca61dae32669 |  gri      |
And an invalid token in Omlink
When I send a GET request for omlink prefill endpoint URL
Then validate the response of the omlink prefill endpoint with invalid token
| statuscode  |success   |
| 401         |false     |

@Get_call @ValidateOmlinkPrefillURLResponse_InvalidCompany
Scenario: Validate the response of the quote service endpoint with invalid company
Given a omlink prefill endpoint URL
| loanID                               |  Company  |
| 1000daf8-9701-4d59-8b15-ca61dae32669 |  abc      |
And a valid token in Omlink
When I send a GET request for omlink prefill endpoint URL
Then validate the response of the omlink prefill endpoint with invalid company
| success  | statuscode |
| false    | 400        |

@Get_call @ValidateOmlinkPrefillURLResponse_InvalidEndpoint
Scenario: Validate the response of the quote service endpoint with invalid endpoint
Given a omlink prefill endpoint URL with invalid endpoint
| loanID                               |  Company  |
| 1000daf8-9701-4d59-8b15-ca61dae32669 |  gri      |
And a valid token in Omlink
When I send a GET request for omlink prefill endpoint URL with invalid endpoint
Then validate the response of the omlink prefill endpoint with invalid endpoint
| statuscode |
| 403        |