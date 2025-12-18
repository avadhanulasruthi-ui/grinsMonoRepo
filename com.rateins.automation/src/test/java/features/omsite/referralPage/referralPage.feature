@referralPage @referralHomePage
Feature: Validate the functionality on referral Home Page of Application 

@referralHomePageValidation
Scenario: Verify if the User is on Referral Home Page
	Given launch the chrome browser
	And launch the referral home page
	Then validate whether user is on the referral home page
	And close chrome browser

@referralHomePageValidation
Scenario: Verify if the User is able to enter Valid data on Referral Home Page
	Given launch the chrome browser
	And launch the referral home page
	Then validate whether user is able to enter valid data on referral home page
	| FirstName | LastName | PhoneNumber | EmailAddress       |
  | John      | Hopkins  | 5555555555  |TestEmail@gmail.com |
	And close chrome browser

@referralNextPageValidation
Scenario: Verification of Next Page Navigation from Referral Home Page
	Given launch the chrome browser
	And launch the referral home page
	Then validate whether user is able to navigate to next page from Referral Home Page
	| FirstName | LastName | PhoneNumber | EmailAddress       |
  | John      | Hopkins  | 5555555555  |TestEmail@gmail.com |
	And close chrome browser

@referralNextPage     @ValidationInValidPhoneNumber
Scenario: Verification of Next Page Navigation from Referral Home Page when user enters invalid phone number
	Given launch the chrome browser
	And launch the referral home page
	Then validate whether user is able to navigate to next page when user enters invalid phone number
	| FirstName | LastName | PhoneNumber | EmailAddress       |
  | John      | Hopkins  | 55555555    |TestEmail@gmail.com |
	And close chrome browser

@referralNextPage     @ValidationInValidFirstName
Scenario: Verification of Next Page Navigation from Referral Home Page when user enters invalid first name
	Given launch the chrome browser
	And launch the referral home page
	Then validate whether user is able to navigate to next page when user enters invalid first name
	| FirstName | LastName | PhoneNumber | EmailAddress       |
  |           | Hopkins  | 5555555555  |TestEmail@gmail.com |
	And close chrome browser

@referralNextPage     @ValidationInValidLastName
Scenario: Verification of Next Page Navigation from Referral Home Page when user enters invalid last name
	Given launch the chrome browser
	And launch the referral home page
	Then validate whether user is able to navigate to next page when user enters invalid last name
	| FirstName | LastName | PhoneNumber | EmailAddress       |
  | John      |          | 5555555555  |TestEmail@gmail.com |
	And close chrome browser

@referralNextPage     @ValidationInValidEMailAddress
Scenario: Verification of Next Page Navigation from Referral Home Page when user enters invalid email address
	Given launch the chrome browser
	And launch the referral home page
	Then validate whether user is able to navigate to next page when user enters invalid email address
	| FirstName | LastName | PhoneNumber |
  | John      | Hopkins  | 5555555555  |
	And close chrome browser

@referralHomeValidation
Scenario: Verification of Referral Home Page Title and all the elements on the Page
	Given launch the chrome browser
	And launch the referral home page
	Then validate all the elements on referral home page and page title
	And close chrome browser

@referralHomePageSupportDetailsValidation
Scenario: Verification of Support details on Referral Home Page
	Given launch the chrome browser
	And launch the referral home page
	Then validate all the Support details on referral home page
	And close chrome browser

