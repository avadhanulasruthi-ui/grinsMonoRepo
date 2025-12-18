@omsite @Auto @GenerateQuotes_Auto
Feature: Validate the functionality on Add Additional Driver Info page of Omsite Auto flow. 

@Auto @QuotesPage_Auto @Generate_Quotes_For_ValidAddress
Scenario: Verify if the user is able to generate Quotes for valid address in Quotes page
	Given Open the chrome browser
	When launch the Auto flow url
	Then validate whether user is able to enter valid user data in driverInfo page
	| FirstName | LastName | Month | Day  | Year  | PhoneNumber |   EmailAddress   |
  | John      | Hopkins  | 01    | 01   | 1990  | 8888888899  | john.h@email.com |
	And validate whether user is able to navigate to Would you like to add additional driver page
	And validate whether user is able to navigate to Garage Info collection page without adding additional driver info
	And validate whether user is able to enter valid address in GarageInfo page
	| Address                | City    | State | ZipCode    | 
  | 2204 Stratingham Drive | Dublin  | OH    | 43016      |
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
	And User clicks on No radio button
	And Click on Next and navigate to Quotes page
	And Quotes must be generated for valid address
	And close the chrome browser
	
@Auto @GenerateQuotes_Auto @Generate_Quotes_For_ValidAddress_AdditionalDrivers_AdditionalVehicles
Scenario: Verify if the user is able to generate Quotes for valid address and Five additional Drivers and Four additional Vehicles in Quotes page
	Given Open the chrome browser
	When launch the Auto flow url
	Then validate whether user is able to enter valid user data in driverInfo page
	| FirstName | LastName | Month | Day  | Year  | PhoneNumber |   EmailAddress   |
  | John      | Hopkins  | 01    | 01   | 1990  | 8888888899  | john.h@email.com |
	And validate whether user is able to navigate to Would you like to add additional driver page
	And validate whether user is able to enter valid details in Additional driver page
	| FirstName | LastName | Month | Day  | Year  |
  | Driver    | One      | 01    | 01   | 1991  |
  | Driver    | Two      | 02    | 02   | 1992  |
  | Driver    | Three    | 03    | 03   | 1993  |
  | Driver    | Four     | 04    | 04   | 1994  |
  | Driver    | Five     | 05    | 05   | 1995  |
  And validate whether user is able click on No and navigate to GarageInfo page
	And validate whether user is able to enter valid address in GarageInfo page
	| Address                | City        | State | ZipCode    | 
  | 2204 Stratingham Drive | Dublin      | OH    | 43016      |
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
	And validate whether user is able to enter valid details for Four additional vehicles
	| Year | Make | Model       | SubModel                    |  NoOfMiles  |
	| 2020 | AUDI | A3 KOMFORT  | CONVERTIBLE 2 AWD 2.0L Gas  |  11000      |
  And Quotes must be generated for valid address
	And close the chrome browser
	
	@Auto @GenerateQuotes_Auto @Generate_Quotes_For_ValidAddress_5AdditionalDrivers_5AdditionalVehicles
Scenario: Verify if the user is able to generate Quotes for valid address and Five additional Drivers and Five additional Vehicles in Quotes page
	Given Open the chrome browser
	When launch the Auto flow url
	Then validate whether user is able to enter valid user data in driverInfo page
	| FirstName | LastName | Month | Day  | Year  | PhoneNumber |   EmailAddress   |
  | John      | Hopkins  | 01    | 01   | 1990  | 8888888899  | john.h@email.com |
	And validate whether user is able to navigate to Would you like to add additional driver page
	And validate whether user is able to enter valid details in Additional driver page
	| FirstName | LastName | Month | Day  | Year  |
  | Driver    | One      | 01    | 01   | 1991  |
  | Driver    | Two      | 02    | 02   | 1992  |
  | Driver    | Three    | 03    | 03   | 1993  |
  | Driver    | Four     | 04    | 04   | 1994  |
  | Driver    | Five     | 05    | 05   | 1995  |
  And validate whether user is able click on No and navigate to GarageInfo page
	And validate whether user is able to enter valid address in GarageInfo page
	| Address                | City        | State | ZipCode    | 
  | 2204 Stratingham Drive | Dublin      | OH    | 43016      |
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
	And validate whether user is able to enter valid details for Five additional vehicles
	| Year | Make | Model       | SubModel                    |  NoOfMiles  |
	| 2020 | AUDI | A3 KOMFORT  | CONVERTIBLE 2 AWD 2.0L Gas  |  11000      |
  And Click on Next and navigate to Quotes page
  And close the chrome browser