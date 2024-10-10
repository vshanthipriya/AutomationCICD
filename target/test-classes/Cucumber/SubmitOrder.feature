
@tag
Feature: Purchase the order from Ecommerce website

	Background:
	Given I landed on Ecommerce page

  @Regression
  Scenario Outline: Positive Test of submitting the order
    Given logged in with username <name> and password <password>
    When I add product <productName> from cart
    And checkout <productName> and submit the order
    Then "Thankyou for the order." message is displayed on confirmation page

    Examples: 
      | name  				|	password  | productName	|
      | priya@xyz.com	| Shanthi@12| ZARA COAT 3	|
      
