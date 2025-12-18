@omsite @Auto @AdditionalVehicleInfoPage_Auto
Feature: Validate the functionality on Add Additional Vehicle Info page of Omsite Auto flow. 

@AdditionalVehicleInfoPage_Auto @Add_Additional_Vehicle_Auto
Scenario: Verify if user is able to click on Yes and navigate to addtional vehicle info collection page
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
  And User is on Add Additional vehicle details page
	And validate whether user is able to click on Yes and navigate to addtional vehicle info collection page
	And close the chrome browser
	
@AdditionalVehicleInfoPage_Auto @Provide_Valid_Details_AdditionalVehicle_Auto
Scenario: Verify if user is able to enter valid details in Additional Vehicle page and more details of vehicle page
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
  And User is on Add Additional vehicle details page
	And validate whether user is able to enter valid details in Additional vehicle page and more details of vehicle pages
	|noOfMiles  |
  |11000      |
	And close the chrome browser
	