Feature: Test a successful registration

  Scenario: Successful registration
    Given The cloud login page
    When I register with the name "Timmy" "Tumbles"
    And email "tumblingforever@hotmail.com" and password "aaa"
    Then the registration will be successful