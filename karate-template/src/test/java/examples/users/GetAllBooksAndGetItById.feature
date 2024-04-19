Feature: sample karate test script

  Background:
    * url 'http://localhost:8083/'

  Scenario: get all books and then get the first book by id
    Given path 'books'
    When method get
    Then status 200

    * def first = response[0]

    Given path 'books', first.id
    When method get
    Then status 200