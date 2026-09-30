Feature: SauceDemo login

  @smoke
  Scenario: Successful login with valid credentials
    Given user is on the sauce demo login page
    When user enters valid credentials
    Then products page should be displayed
