@omsite @HO3 @AddingCoinsurerPage_HO3
Feature: Validate the functionality on Property Address page of Application 

Scenario: Verification of Additional Insurer Page Navigation 
	Given Open the browser
	And launch the url			
	Then validate whether user is able to enter valid user data
	| FirstName | LastName | Month | Day  | Year  | PhoneNumber |
  | John      | Hopkins  | 11    | 11   | 2000  | 5555555555  |
	Then validate whether user is able to navigate to next page
	Then validate whether user is on add additional insurer page
	And close the browser	
	
Scenario: Verification if the User is able to add additional Insurer 
	Given Open the browser
	And launch the url			
	Then validate whether user is able to enter valid user data
	| FirstName | LastName | Month | Day  | Year  | PhoneNumber |
  | John      | Hopkins  | 11    | 11   | 2000  | 5555555555  |
	Then validate whether user is able to navigate to next page
	Then validate whether user is able to add additional insurer
	| FirstName | LastName | Month | Day  | Year  |
  | Katherine | Thompson | 06    | 18   | 1996  |
	And close the browser		
	
	Scenario: Verification of property page navigation after adding additional Insurer
	Given Open the browser
	And launch the url			
	Then validate whether user is able to enter valid user data
	| FirstName | LastName | Month | Day  | Year  | PhoneNumber |
  | John      | Hopkins  | 11    | 11   | 2000  | 5555555555  |
	Then validate whether user is able to navigate to next page
	Then validate whether user is able to add additional insurer
	| FirstName | LastName | Month | Day  | Year  |
  | Katherine | Thompson | 06    | 18   | 1996  |
	And validate whether user is able to provide property address
	|  Address                                  | Month | Year  |
	|  1225 S Main St, Georgetown, TX 78626     | 01    | 2022  |
	And close the browser		
	
	Scenario: Verification if user is on quotes page after adding additional Insurer
	Given Open the browser
	And launch the url			
	Then validate whether user is able to enter valid user data
	| FirstName | LastName | Month | Day  | Year  | PhoneNumber |
  | John      | Hopkins  | 11    | 11   | 2000  | 5555555555  |
	Then validate whether user is able to navigate to next page
	Then validate whether user is able to add additional insurer
	| FirstName | LastName | Month | Day  | Year  |
  | Katherine | Thompson | 06    | 18   | 1996  |
	And validate whether user is able to provide property address
	|  Address                                  | Month | Year  |
	|  1225 S Main St, Georgetown, TX 78626     | 01    | 2022  |
	Then validate whether user is able to navigate to quotes page
	And close the browser	
	
	@InvalidDOB_HO3  
	Scenario: Verification of Error message when user enter invalid date of birth on adding additional Insurer
	Given Open the browser
	And launch the url			
	Then validate whether user is able to enter valid user data
	| FirstName | LastName | Month | Day  | Year  | PhoneNumber |
  | John      | Hopkins  | 11    | 11   | 2000  | 5555555555  |
	Then validate whether user is able to navigate to next page
	Then validate the error message when user enter invalid date of birth for the coinsurer
	| FirstName | LastName | Month | Day  | Year  |
  | Katherine | Thompson | 23    | 33   | 1900  |
	And close the browser	
	
	@InvalidDOB_HO3
	Scenario: Verification of Error message when user enter invalid day in date of birth
	Given Open the browser
	And launch the url			
	Then validate whether user is able to enter valid user data
	| FirstName | LastName | Month | Day  | Year  | PhoneNumber |
  | John      | Hopkins  | 11    | 11   | 2000  | 5555555555  |
	Then validate whether user is able to navigate to next page	
	Then validate whether user is clicking on yes button
	When user enters invalid day
	| FirstName | LastName | Month | Day  | Year  |
  | John      | Hopkins  | 11    | 45   | 1992  |
	Then validate whether user able to view the error message for invalid date of birth
	And validate whether the Add button is disabled
	And close the browser
	
	@InvalidDOB_HO3
Scenario: Verification of Error message when user enter invalid month in date of birth
	Given Open the browser
	And launch the url			
	Then validate whether user is able to enter valid user data
	| FirstName | LastName | Month | Day  | Year  | PhoneNumber |
  | John      | Hopkins  | 11    | 11   | 2000  | 5555555555  |
	Then validate whether user is able to navigate to next page	
	Then validate whether user is clicking on yes button
	When user enters invalid month
	| FirstName | LastName | Month | Day  | Year  |
  | John      | Hopkins  | 25    | 18   | 1992  |
	Then validate whether user able to view the error message for invalid date of birth
	And validate whether the Add button is disabled
	And close the browser
	
	@InvalidDOB_HO3
Scenario: Verification of Error message when user enter invalid year in date of birth
	Given Open the browser
	And launch the url			
	Then validate whether user is able to enter valid user data
	| FirstName | LastName | Month | Day  | Year  | PhoneNumber |
  | John      | Hopkins  | 11    | 11   | 2000  | 5555555555  |
	Then validate whether user is able to navigate to next page	
	Then validate whether user is clicking on yes button
	When user enters invalid year
	| FirstName | LastName | Month | Day  | Year  |
  | John      | Hopkins  | 11    | 18   | 2024  |
	Then validate whether user able to view the error message for invalid date of birth
	And validate whether the Add button is disabled
	And close the browser
