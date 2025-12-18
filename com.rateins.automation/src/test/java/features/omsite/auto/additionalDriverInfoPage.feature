@omsite @Auto @AdditionalDriverInfoPage_Auto
Feature: Validate the functionality on Add Additional Driver Info page of Omsite Auto flow. 

@AdditionalDriverInfoPage_Auto @Add_Additional_Driver_Auto
Scenario: Verify if user is able to click on Yes and navigate to addtional driver info collection page
	Given Open the chrome browser
	When launch the Auto flow url
	Then validate whether user is able to enter valid user data in driverInfo page
	| FirstName | LastName | Month | Day  | Year  | PhoneNumber |   EmailAddress   |
  | John      | Hopkins  | 01    | 01   | 1990  | 8888888899  | john.h@email.com |
	And validate whether user is able to navigate to Would you like to add additional driver page
	And validate whether user is able to click on Yes and navigate to addtional driver info collection page
	And close the chrome browser
	
@AdditionalDriverInfoPage_Auto @Provide_Valid_Details_AdditionalDriver_Auto
Scenario: Verify if user is able to enter valid details in Additional driver page
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
	And close the chrome browser
	
