Feature: Test a successful booking

  Scenario Successful booking
    Given I have logged in with email "AFadingDog@hotmail.com" and password "lostpassword"
    When I book a flight from "London" to "Dubai"
    And in seat "12A" and in class "Economy"
    Then the booking will be successful