Feature: sample karate test script

  Background:
    * url 'http://localhost:8083/'

  Scenario: create an author and then get it by id - the author exists
    * def author =
      """
      {
        "name": "Test Author2 Cristiana Neculita",
        "nationality": "romanian",
        "birthDate": "2002-09-10",
        "deathDate": ""
      }
      """

    Given url 'http://localhost:8083/authors'
    And request author
    When method post
    Then status 500

    * def id = response.id
    * print 'created id is: ', id