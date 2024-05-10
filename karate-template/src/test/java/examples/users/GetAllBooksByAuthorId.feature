Feature: sample karate test script

  Background:
    * url 'http://localhost:8083/'

  Scenario: get all books by author id
    Given url 'http://localhost:8083/books/authors/6622d9cdc32d4d0e079ab69f'
    When method get
    Then status 200