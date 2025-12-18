@omsite @Auto @QuotesPage_Auto
Feature: Validate the functionality of Quotes page in Omsite Auto flow. 

Background:
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
	And validate whether user is able to navigate to Vehicle info page when clicked on Next
	And validate whether user is able to enter valid data in all fields of vehicle info page
	| Year      | Make | Model      | SubModel                   |
  | 2020      | AUDI | A3 KOMFORT | CONVERTIBLE 2 AWD 2.0L Gas |
	And validate whether user is able to click on Next and navigate to more vehicle details page
	And validate whether user is able to enter valid data in all fields of more vehicle details page
	| VehicleOwnership | VehicleUsage | NoOfMiles |
	| Owned            | Farming      | 12000     |
	And validate whether user is able to click on Next and navigate to Add Additional vehicle details page

@Auto @QuotesPage_Auto @Navigate_To_Quotes_page
Scenario: Verify if the user is able to navigate to Quotes page without adding additional vehicle info
	Given User is on Add Additional vehicle details page
	When User clicks on No radio button
	Then Click on Next and navigate to Quotes page
	And close the chrome browser

@Auto @QuotesPage_Auto @Verify_Error_Message_On_Quotes_page
Scenario: Verify if the user is able to view an error message in Quotes page
	Given User is on Add Additional vehicle details page
	When User clicks on No radio button
	Then Click on Next and navigate to Quotes page
	And User must view an error message in Quotes pages
	And close the chrome browser
