Feature: sample karate test script

  Background:
    * url 'http://localhost:8083/'

  Scenario: get all books by author id
    Given url 'http://localhost:8083/books/authors/6620160bbe66c449e2079efc'
    When method get
    Then status 200