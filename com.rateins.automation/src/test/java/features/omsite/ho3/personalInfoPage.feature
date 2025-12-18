@omsite	@HO3 @persoanlInfoPage_HO3
Feature: Validate the functionality on Personal information page of Application 

@PersoanlInfoPage_HO3
Scenario: Verify if the User is on Landing page 
	Given Open the browser
	And launch the url			
	Then validate whether user is on the right page
	And close the browser

@PersoanlInfoPage_HO3
Scenario: Verification if the user is able to enter valid data
	Given Open the browser
	And launch the url			
	Then validate whether user is able to enter valid user data
	| FirstName | LastName | Month | Day  | Year  | PhoneNumber |
  | John      | Hopkins  | 11    | 11   | 2000  | 5555555555  |
	And close the browser

@PersoanlInfoPage_HO3
Scenario: Verification of Next Page Navigation 
	Given Open the browser
	And launch the url			
	Then validate whether user is able to enter valid user data
	| FirstName | LastName | Month | Day  | Year  | PhoneNumber |
  | John      | Hopkins  | 11    | 11   | 2000  | 5555555555  |
	Then validate whether user is able to navigate to next page
	And close the browser

@PersoanlInfoPage_HO3
Scenario: Verification of Additional Insurer Page Navigation 
	Given Open the browser
	And launch the url			
	Then validate whether user is able to enter valid user data
	| FirstName | LastName | Month | Day  | Year  | PhoneNumber |
  | John      | Hopkins  | 11    | 11   | 2000  | 5555555555  |
	Then validate whether user is able to navigate to next page
	Then validate whether user is on add additional insurer page
	And close the browser	

@PersoanlInfoPage_HO3
Scenario: Verification of marital status dropdown
	Given Open the browser
	And launch the url
	Then validate the marital status data
	| FirstName | LastName | Month | Day  | Year  | PhoneNumber |
  | John      | Hopkins  | 11    | 11   | 2000  | 5555555555  |
	And close the browser

@PersoanlInfoPage_HO3 @NegativeScenario_HO3 @InvalidDOB_HO3
Scenario: Verification of Error message when user enter invalid date of birth
	Given Open the browser
	And launch the url			
	When user enters invalid date
	| FirstName | LastName | Month | Day  | Year  | PhoneNumber |
  | John      | Hopkins  | 15    | 24   | 1992  | 5555555555  |
	Then validate whether user able to view the error message for invalid date of birth
	And validate whether the Next button is disabled
	And close the browser		

@PersoanlInfoPage_HO3 @NegativeScenario_HO3 @InvalidDOB_HO3
Scenario: Verification of Error message when user enter invalid day in date of birth
	Given Open the browser
	And launch the url			
	When user enters invalid day
	| FirstName | LastName | Month | Day  | Year  |
  | John      | Hopkins  | 11    | 45   | 1992  |
	Then validate whether user able to view the error message for invalid date of birth
	And validate whether the Next button is disabled
	And close the browser
	
@PersoanlInfoPage_HO3 @NegativeScenario_HO3 @InvalidDOB_HO3
Scenario: Verification of Error message when user enter invalid month in date of birth
	Given Open the browser
	And launch the url			
	When user enters invalid month
	| FirstName | LastName | Month | Day  | Year  |
  | John      | Hopkins  | 25    | 18   | 1992  |
	Then validate whether user able to view the error message for invalid date of birth
	And validate whether the Next button is disabled
	And close the browser
	
@PersoanlInfoPage_HO3 @NegativeScenario_HO3 @InvalidDOB_HO3
Scenario: Verification of Error message when user enter invalid year in date of birth
	Given Open the browser
	And launch the url			
	When user enters invalid year
	| FirstName | LastName | Month | Day  | Year  |
  | John      | Hopkins  | 11    | 18   | 2024  |
	Then validate whether user able to view the error message for invalid date of birth
	And validate whether the Next button is disabled
	And close the browser
	
@PersoanlInfoPage_HO3 @NegativeScenario_HO3 @Invalidphonenumber_HO3
Scenario: Verification of next page navigation when user enter invalid phone number
	Given Open the browser
	And launch the url
	When user enters invalid phone number
	| FirstName | LastName | Month | Day  | Year  | PhoneNumber |
  | John      | Hopkins  | 11    | 18   | 1992  | 55555555    |
	And validate whether the Next button is disabled
	And close the browser
	