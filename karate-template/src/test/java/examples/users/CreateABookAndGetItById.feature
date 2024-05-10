Feature: sample karate test script

  Background:
    * url 'http://localhost:8083/'

  Scenario: create a book and then get it by id
    * def book =
      """
      {
    "title": "Poems by Cosmina Duca",
    "genre": "poems",
    "publicationDate": "2024-04-17",
    "publisher": "Cosmina Duca",
    "authorId": "6622d9cdc32d4d0e079ab69f"
}
      """

    Given url 'http://localhost:8083/books'
    And request book
    When method post
    Then status 200
    And match response.bookId == '#string'

    * def id = response.bookId
    * print 'created id is: ', id
