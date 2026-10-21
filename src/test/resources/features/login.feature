@login
Feature: Login

  Background:
    Given the user is on the login screen

  @positive
  Scenario: Successful login shows the Dashboard
    When the user logs in with valid credentials
    Then the Dashboard is displayed

  @negative
  Scenario: Login with incorrect credentials shows an error
    When the user logs in with <credentials>
    Then the invalid credentials error is displayed

    Examples:
    | credentials           |
    | an unregistered email |
    | a wrong password      |

  @negative
  Scenario: Login with incomplete data empty keeps the login button disable
    When the user enters email "<email>" and password "<password>"
    Then the login button is disabled

    Examples:
    | email               | password |
    |                     |          |
    | someone@example.com |          |
    |                     | anything |
