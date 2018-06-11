Feature: Open Vehicle information search page

Scenario: Open vehicle information search page and verify details
    Given web driver
        And page link "https://www.gov.uk/get-vehicle-information-from-dvla"
    When I click Start now button
    Then I should redirect to vehicle information search page
        And I should see header "Enter the registration number of the vehicle"
        And I should see a form label with text "Registration number (number plate)"
        And I should see a text "For example, CU57ABC"
        And I should see a text box to enter vehicle registration number
        And I should see button "Continue"