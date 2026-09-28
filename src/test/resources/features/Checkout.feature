Feature: SauceDemo Checkout feature

  @regression
  Scenario: Verify add products to cart
    Given user is on the sauce demo login page
    When user enters valid credentials
    And user adds backpack to the cart
    And user adds bike light to the cart
    And user opens shopping cart
    Then backpack and bike light should be displayed in the cart page

  @regression
  Scenario: Verify complete checkout
    Given user is on the sauce demo login page
    When user enters valid credentials
    And user adds backpack to the cart
    And user adds bike light to the cart
    And user opens shopping cart
    And user checkout the products from cart page
    And user enters checkout information
    And user clicks continue
    And user clicks finish from checkout overview page
    Then user should be redirected to confirmation page