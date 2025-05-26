Feature: Flight search on MakeMyTrip

  Scenario: Search flights from HYD to MAA
    Given The user opens the MakeMyTrip website
    When The user clicks on "Flights"
    And The user selects "ROUND TRIP"
    And The user enters "HYD" in the from field
    And The user enters "MAA" in the to field
    And The user selects the departure date
    And The user selects the return date
    And The user clicks on the "Search" button
    Then The search results page is displayed