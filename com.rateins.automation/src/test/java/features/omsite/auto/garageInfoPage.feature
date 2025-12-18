@omsite @Auto @GarageInfoPage_Auto
Feature: Validate the functionality on Garage Info Collection page of Omsite Auto flow. 

@Auto @GarageInfoPage_Auto
Scenario: Verify if the user is able to navigate to Garage Info collection page without adding additional driver info
	Given Open the chrome browser
	When launch the Auto flow url
	Then validate whether user is able to enter valid user data in driverInfo page
	| FirstName | LastName | Month | Day  | Year  | PhoneNumber |   EmailAddress   |
  | John      | Hopkins  | 01    | 01   | 1990  | 8888888899  | john.h@email.com |
	And validate whether user is able to navigate to Would you like to add additional driver page
	And validate whether user is able to navigate to Garage Info collection page without adding additional driver info
	And close the chrome browser
	
@GarageInfoPage_Auto   @ValidData_Auto
Scenario: Verify if the user is able to enter valid user data in GarageInfo page
	Given Open the chrome browser
	When launch the Auto flow url
	Then validate whether user is able to enter valid user data in driverInfo page
	| FirstName | LastName | Month | Day  | Year  | PhoneNumber |   EmailAddress   |
  | John      | Hopkins  | 01    | 01   | 1990  | 8888888899  | john.h@email.com |
	And validate whether user is able to navigate to Would you like to add additional driver page
	And validate whether user is able to navigate to Garage Info collection page without adding additional driver info
	And validate whether user is able to enter valid user data in GarageInfo page
	| Address                 | City        | State | ZipCode    | 
  | 1225 South Main Street  | Georgetown  | TX    | 78626      |
	And close the chrome browser
	
@NegativeScenario_Auto @InvalidPropertyAddress_Auto
Scenario: Verification of next page navigation when user enter invalid property address
	Given Open the chrome browser
	When launch the Auto flow url
	Then validate whether user is able to enter valid user data in driverInfo page
	| FirstName | LastName | Month | Day  | Year  | PhoneNumber |   EmailAddress   |
  | John      | Hopkins  | 01    | 01   | 1990  | 8888888899  | john.h@email.com |
	And validate whether user is able to navigate to Would you like to add additional driver page
	And validate whether user is able to navigate to Garage Info collection page without adding additional driver info
	Then validate user navigation when enter invalid property address in Garage info page
	|  Address  |  SuiteNumber  |  City     | ZipCode  |
	| 111111111 |   111111111   | 111111111 |  11111   |
	And close the chrome browser

@NegativeScenario_Auto @InvalidPropertyAddress_Auto
Scenario: Verification of next page navigation when user enter invalid property address
	Given Open the chrome browser
	When launch the Auto flow url
	Then validate whether user is able to enter valid user data in driverInfo page
	| FirstName | LastName | Month | Day  | Year  | PhoneNumber |   EmailAddress   |
  | John      | Hopkins  | 01    | 01   | 1990  | 8888888899  | john.h@email.com |
	And validate whether user is able to navigate to Would you like to add additional driver page
	And validate whether user is able to navigate to Garage Info collection page without adding additional driver info
	Then validate user navigation when enter only city and zipcode in property address
	|  City     | ZipCode  |
	|  Chicago  | 60605    |
	And close the chrome browser