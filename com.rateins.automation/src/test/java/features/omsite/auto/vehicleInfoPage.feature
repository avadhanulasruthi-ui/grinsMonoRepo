@omsite @Auto @VehicleInfoPage_Auto
Feature: Validate the functionality on Vehicle Info page of Omsite Auto flow. 

@VehicleInfoPage_Auto
Scenario: Verify if the User is on VehicleInfo page
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
	And close the chrome browser

@VehicleInfoPage_Auto @ValidData_VehicleInfoPage_Auto
Scenario: Verify if the user is able to enter valid data in all fields of Vehicle info page
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
	And close the chrome browser

@VehicleInfoPage_Auto @MoreVehicleDetails_Auto
Scenario: Verify if the user is able to navigate to More vehicle details page
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
	And close the chrome browser
	
@VehicleInfoPage_Auto @ValidData_MoreVehicleDetails_Auto
Scenario: Verify if the user is able to enter valid data in more vehicle details page
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
	And close the chrome browser
	
@VehicleInfoPage_Auto @Add_Additional_Vehicle_Page
Scenario: Verify if the user is able to navigate to Add Additional Vehicle details page
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
  And close the chrome browser

	@NegativeScenario_Auto  @NoValeuselected_Auto
Scenario: Verification of next page navigation when user does not select any values in Vehicle info page
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
	And validate whether the Next button is disabled in vehicle info page
	And close the chrome browser
	
	@NegativeScenario_Auto  @OnlyYearselected_Auto
Scenario: Verification of next page navigation when user select only vehicle year of manufacture field in Vehicle info page
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
	Then validate whether user able to select only vehicle year in vehicle info page
	| Year      |
	| 2020      |
	And validate whether the Next button is disabled in vehicle info page
	And close the chrome browser
	
	@NegativeScenario_Auto  @OnlyYearandmakeselected_Auto
Scenario: Verification of next page navigation when user select only vehicle year of manufacture and Make fields in Vehicle info page
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
	Then validate whether user able to select only vehicle year and make in vehicle info page
	| Year      | Make |
  | 2020      | AUDI |
	And validate whether the Next button is disabled in vehicle info page
	And close the chrome browser
	
	@NegativeScenario_Auto  @OnlyYearmakemodelselected_Autos
Scenario: Verification of next page navigation when user select only vehicle year of manufacture and Make and Model fields in Vehicle info page
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
	Then validate whether user able to select only vehicle year and make and model in vehicle info page
	| Year      | Make | Model      |
  | 2020      | AUDI | A3 KOMFORT |
	And validate whether the Next button is disabled in vehicle info page
	And close the chrome browser