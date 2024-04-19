Feature: sample karate test script

  Background:
    * url 'http://localhost:8083/'

  Scenario: get all authors and then get the first author by id
    Given path 'authors'
    When method get
    Then status 200

    * def first = response[0]

    Given path 'authors', first.id
    When method get
    Then status 200