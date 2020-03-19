Feature: Verify Calculator functionalities

  @Test
  Scenario: Checking HealthCheck
    When I click on HealthCheck
    Then Response should be successful

  @Test
  Scenario: Checking HelloWorld
    When I click on HelloWorld
    Then Response should be successful


  @Test
  Scenario Outline: User login
    Given a running application
    When a user enters <username> and <password>
    Then login should return <result>

    Examples:
      | username   | password  | result    |
      | "akhila"   | "akhi123" | "success" |
      | "testing1" | "testing" | "success" |

  @Test1
  Scenario Outline: Getting patient details
    Given a running application
    When a user gives <patientName>
    Then response should give PatientDetails

    Examples:
      | patientName |
      | "akhila"    |
      | "testing1"  |

  @Test12
  Scenario Outline: User will signup and insertDetails and verify those
    Given a running application
    And a user signup with details <username> and <password> and <email>
    And verify login for <username> and <password>
    And enter patient details <username> and <FirstName> and <LastName> and <Age> and <Phone> and <Disease> and <Medications>
    And a user gives <patientName>
    Then response should give PatientDetails

    Examples:
      | username   | FirstName | LastName | Age | Phone  | Disease  | Medications | password   | email                 | patientName |
      | "JohnMike15" | "John"    | "Mike"   | 25  | 123456 | "fever"  | "advil"     | "test123"  | "joke.mike@gmail.com"   | "JohnMike15"  |
#      | "Bunny2"    | "Bunny"   | "Sunny"  | 35  | 654123 | "Corona" | "Sanitizer" | "bunny123" | "sunny.bunny@gmail.com" | "Bunny2"     |