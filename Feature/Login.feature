Feature: Login with valid credentials

  @sanity
  Scenario: Successful with login credentials
    Given user lanch the browser
    And opens URL "http://localhost/opencart/upload/"
    When User navigate to MyAccount menu
    And click on Login
    And User enters Email as "abc1040@gmail.com" and Password as "Kutty123"
    And Click on Login button
    Then User navigates to MyAccount Page
