Feature: Test a successful login

  Scenario: Successful login
    Given A cloud login page
    When we login with email "notarealmail@hotmail.com" and password "password"
    Then the login will be successful