Feature: Login feature
  @test
  Scenario: user test login feature with correct username and password
    Given user is on login page
    Then user will enter username
    Then user will enter password
    Then uesr will clicks on login
    Then user verify is logged in