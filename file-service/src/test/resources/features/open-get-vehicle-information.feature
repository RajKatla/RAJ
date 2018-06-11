Feature: Open Get vehicle information from DVLA page

Scenario: Open Get vehicle information from DVLA page and verify details
    Given a web driver
        And a link "https://www.gov.uk/get-vehicle-information-from-dvla"
    When I open above link
    Then I should see Get vehicle information from DVLA page
        And I should see first text "Check online to find out what information the Driver and Vehicle Licensing Agency (DVLA) holds about a vehicle."
        And I should see second text "You’ll need the vehicle’s registration number."
        And I should see "Start now" button