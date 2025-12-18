@omsite @HO3 @AddressInfoPage_HO3
Feature: Validate the functionality on Property Address page of Application 

@AddressInfoPage_HO3
Scenario: Verification of Providing Property Address
	Given Open the browser
	And launch the url			
	Then validate whether user is able to enter valid user data
	| FirstName | LastName | Month | Day  | Year  | PhoneNumber |
  | John      | Hopkins  | 11    | 11   | 2000  | 5555555555  |
	And validate whether user is able to navigate to next page
	Then validate whether user is on add additional insurer page
	And validate whether user is able to provide property address
	|  Address                                  | Month | Year  |
	|  1225 S Main St, Georgetown, TX 78626     | 01    | 2022  |
	And close the browser

	
@AddressInfoPage_HO3 @NegativeScenario_HO3 @InvalidPurchaseDate_HO3
Scenario: Verification of error message for invalid purchase date on address page
	Given Open the browser
	And launch the url			
	Then validate whether user is able to enter valid user data
	| FirstName | LastName | Month | Day  | Year  | PhoneNumber |
  | John      | Hopkins  | 11    | 11   | 2000  | 5555555555  |
	And validate whether user is able to navigate to next page
	Then validate whether user is on add additional insurer page
	Then validate the error messgae for invalid purchase date
	|  Address                                  | Month | Year  |
	|  1225 S Main St, Georgetown, TX 78626     | 24    | 2024  |
	And close the browser
	
@AddressInfoPage_HO3 @NegativeScenario_HO3 @InvalidPropertyAddress_HO3
Scenario: Verification of next page navigation when user enter invalid property address
	Given Open the browser
	And launch the url			
	Then validate whether user is able to enter valid user data
	| FirstName | LastName | Month | Day  | Year  | PhoneNumber |
  | John      | Hopkins  | 11    | 11   | 2000  | 5555555555  |
	And validate whether user is able to navigate to next page
	Then validate whether user is on add additional insurer page
	Then validate the error messgae for invalid property address
	|  Address   | Apartment | City      | Zip   | Month | Year  |
	|  111111111 | 111111111 | 111111111 | 11111 | 12    | 2020  |
	And close the browser
	
@AddressInfoPage_HO3 @NegativeScenario_HO3 @ErrorQuotesPage_HO3
Scenario: Verification of error message on quotes page
	Given Open the browser
	And launch the url		
	Then validate whether user is able to enter valid user data
	| FirstName | LastName | Month | Day  | Year  | PhoneNumber |
  | John      | Hopkins  | 11    | 11   | 2000  | 5555555555  |
	And validate whether user is able to navigate to next page
	Then validate whether user is on add additional insurer page
	Then validate the error message on quotes screen
	| Address    | City        | State | ZipCode    | Month | Year |
  | 121 N      | Chicago     | IL    | 60602      | 03    | 1000 |
	And close the browser
	