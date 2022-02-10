@Fact
Feature:  Get Random fact
  Background:
   Given host "https://catfact.ninja"

 #1:Validate the response of the service when wanting to obtain fact with a maximum length established
  @test @GetRandomFact
  Scenario Outline: Get fact with a maximum length established
    Given a timeout of 5000 milliseconds
    When a GET request is sent to endpoint "/fact" with query parameters
      |max_length |<max_length> |
    Then status code should be 200
    And response content type should be json


    Examples: GetRandomFact
      |max_length |
      |95         |
      |100        |


  #2: Validate the response of the service when entering URLs incorrectly
  @test @WrongUrl
  Scenario: Get random fact with Wrong Url
    Given a timeout of 5000 milliseconds
    When a GET request is sent to endpoint "/fatt" with query parameters
      |max_length |495 |
    Then status code should be 404
    And json response should contain key "message" with value string "Not Found"




 # 3: Validate the response from the service by entering an invalid syntax with special characters in the max_length parameter
  @test @WrongMax_length
  Scenario Outline: Get random fact with Wrong Max_length
    Given a timeout of 5000 milliseconds
    When a GET request is sent to endpoint "/fact" with query parameters
      |max_length |<max_length>  |
    Then status code should be 400

    Examples:
      |max_length |
      |$%&&·       |



 # 4: Validate service response by entering non-integer number in max_length parameter
  @test @Max_lengthNoInteger
  Scenario Outline: Get fact Fact Max_length No Integer
    Given a timeout of 5000 milliseconds
    When a GET request is sent to endpoint "/fact" with query parameters
      |max_length |<max_length>|
    Then status code should be 400

    Examples:
      |max_length |
      |34,5       |

 # 5: Validate the response of the service when it does not exist fact with the max_length parameter entered
  @test @GetFactMax_length
  Scenario Outline: Get fact
    Given a timeout of 5000 milliseconds
    When a GET request is sent to endpoint "/fact" with query parameters
      |max_length |<max_length>|
    Then status code should be 204

    Examples:
      |max_length |
      |5          |
      |9          |
      |10         |
      |11         |