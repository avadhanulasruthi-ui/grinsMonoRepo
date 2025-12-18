@omsite @Auto @DriverInfoPage_Auto
Feature: Validate the functionality on Driver Info page of Omsite Auto flow. 

@DriverInfoPage_Auto
Scenario: Verify if the User is on DriverInfo page 
	Given Open the chrome browser
	When launch the Auto flow url
	Then validate whether user is on driverInfo page
	And close the chrome browser

@DriverInfoPage_Auto @ValidData_Auto
Scenario: Verify if the user is able to enter valid data in all fields of Driver info page
	Given Open the chrome browser
	When launch the Auto flow url
	Then validate whether user is able to enter valid user data in driverInfo page
	| FirstName | LastName | Month | Day  | Year  | PhoneNumber |  EmailAddress    |
  | John      | Hopkins  | 01    | 01   | 1990  | 8888888899  | john.h@email.com |
	And close the chrome browser

@DriverInfoPage_Auto @AdditionalDriver_Auto
Scenario: Verify if the user is able to navigate to Would you like to add additional driver page
	Given Open the chrome browser
	When launch the Auto flow url
	Then validate whether user is able to enter valid user data in driverInfo page
	| FirstName | LastName | Month | Day  | Year  | PhoneNumber |   EmailAddress   |
  | John      | Hopkins  | 01    | 01   | 1990  | 8888888899  | john.h@email.com |
	And validate whether user is able to navigate to Would you like to add additional driver page
	And close the chrome browser	

@NegativeScenario_Auto @InvalidDOB_Auto
Scenario: Verification of Error message when user enter invalid date of birth
	Given Open the chrome browser
	When launch the Auto flow url		
	When user enters invalid data in driver info page
	| FirstName | LastName | Month | Day  | Year  | PhoneNumber |   EmailAddress   |
  | John      | Hopkins  | 15    | 40   | 2024  | 8888888899  | john.h@email.com |
	Then validate whether user able to view the error message for invalid date of birth in driver info page
	And validate whether the Next button is disabled in driver info page
	And close the chrome browser
	
@NegativeScenario_Auto @InvalidDOB_Auto
Scenario: Verification of Error message when user enter invalid day in date of birth
	Given Open the chrome browser
	When launch the Auto flow url			
	When user enters invalid day in driver info page
	| FirstName | LastName | Month | Day  | Year  |
  | John      | Hopkins  | 11    | 40   | 1992  |
	Then validate whether user able to view the error message for invalid date of birth in driver info page
	And validate whether the Next button is disabled in driver info page
	And close the chrome browser	
	
@NegativeScenario_Auto @InvalidDOB_Auto
Scenario: Verification of Error message when user enter invalid month in date of birth
	Given Open the chrome browser
	When launch the Auto flow url		
	When user enters invalid month in driver info page
	| FirstName | LastName | Month | Day  | Year  |
  | John      | Hopkins  | 15    | 15   | 1992  |
	Then validate whether user able to view the error message for invalid date of birth in driver info page
	And validate whether the Next button is disabled in driver info page
	And close the chrome browser	
	
@NegativeScenario_Auto @InvalidDOB_Auto
Scenario: Verification of Error message when user enter invalid year in date of birth
	Given Open the chrome browser
	When launch the Auto flow url		
	When user enters invalid year in driver info page
	| FirstName | LastName | Month | Day  | Year  |
  | John      | Hopkins  | 11    | 15   | 2024  |
	Then validate whether user able to view the error message for invalid date of birth in driver info page
	And validate whether the Next button is disabled in driver info page
	And close the chrome browser	
	
@NegativeScenario_Auto @Invalidphonenumber_Auto
Scenario: Verification of next page navigation when user enter invalid phone number
	Given Open the chrome browser
	When launch the Auto flow url		
	When user enters invalid phone number in driver info page
	| FirstName | LastName | Month | Day  | Year  | PhoneNumber | 
  | John      | Hopkins  | 11    | 15   | 1992  | 7777777     |
	And validate whether the Next button is disabled in driver info page
	And close the chrome browser	