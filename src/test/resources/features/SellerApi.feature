Feature: Test Seller API
  @getSellerVerifyEmailNotEmpty @regression
  Scenario: get a single seller and verify seller email is not empty
    Given User hits get single seller api with "/api/myaccount/sellers/"
    Then verify seller email is not empty



  @getAllSeller @regression
  Scenario: get all seller and verify seller is not 0
    Given User hit get get all seller api with "/api/myaccount/sellers"
    Then verify seller ids are not equal to 0


    @updatedSeller  @regression
    Scenario: get single seller, update the same seller, verify seller was updated.
      Given User hits get single seller api with "/api/myaccount/sellers/5726"
      Then verify seller email is not empty
      Then user hit put api with "/api/myaccount/sellers/"
      Then verify user email was updated
      And verify user first name was updated


    @ArchivedSeller  @regression
    Scenario: get single seller and archive the seller and verify seller was archived
      Given User hits get single seller api with "/api/myaccount/sellers/5726"
      Then User hits archive api with "/api/myaccount/sellers/archive/unarchive"
      Then User hit get all unarchive seller api with "/api/myaccount/sellers"


      @CreateDelete  @regression
      Scenario: Create a seller, delete a seller, verify seller was deleted
        Given user hit post api with "/api/myaccount/sellers"
        Then verify seller id was generated
        Then verify seller name is not empty
        And verify seller email is not empty
        Then delete the seller with "/api/myaccount/sellers/"
        Then User hit get get all seller api with "/api/myaccount/sellers"
        Then verify deleted seller is not on the list




