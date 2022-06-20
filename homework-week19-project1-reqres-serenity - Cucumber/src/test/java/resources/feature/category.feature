@user
Feature: Testing different request on the product

  Scenario: I get all users from application
    Given I am on homepage of application of user
    When I send Get request to list endpoint of user
    Then I must get back a valid status code 200 of user

  Scenario: I create user from application
    Given I am on homepage of application of user
    When I send Post request to list endpoint of user
    Then I must get back a valid status code 201 of user

  Scenario: I login with valid credentials to application
    Given I am on homepage of application of user
    When I send Post request to login endpoint of user
    Then I must get back a valid status code 201 of user

  Scenario: I delete user from application
    Given I am on homepage of application of user
    When I send Delete request to list endpoint of user
    Then I must get back a valid status code 204 of user
    And I validate if user is deleted

  @Test
  Scenario: I verify following data response
    Given I am on homepage of application of user
    When I send Get request to list endpoint of user
    Then I validate page "1"
    And I validate per_page "6"
    And I validate totalID "2"
    And I validate name of Id "Eve"
    And I validate list of data 6
    And I validate avatar "https://reqres.in/img/faces/6-image.jpg"
    And I validate supportUrl "https://reqres.in/#support-heading"
    And I validate supportText "To keep ReqRes free, contributions towards server costs are appreciated!"


