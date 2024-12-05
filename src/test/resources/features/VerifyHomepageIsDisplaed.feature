Feature: Login with correct credential

  Background: 
    Given User access to URL browser

  Scenario: Verify Homepage is displayed when logging in with correct credential
    When User set text "dbd@gmail.com" to Email Address field
    And User set text "Zuong123!@#" to Password field
		And User click on submit button
		Then Verify User is landed to Homepage
