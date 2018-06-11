Feature: Search vehicle using registration number

Scenario Outline: Search vehicle using registration number
Given Web driver
    And link "https://vehicleenquiry.service.gov.uk/"
When I search vehicle with registration number <regNumber>
    And click continue button
Then I should see vehicle details <make> and <colour>
Examples:
      | regNumber | make         | colour |
      | "LP11XKF" | "VOLKSWAGEN" |"BLACK" |
      | "AY14UAF" | "MAZDA"      |"SILVER"|
      | "YP16VPV" | "FORD"       |"BLACK" |
      | "LD08PXL" | "AUDI"       |"GREY"  |
      | "KW65DMX" | "CITROEN"    |"RED"   |