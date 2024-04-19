package com.example.tss_project_withspring.Data;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<BookEntity, String> {
    List<BookEntity> findByAuthor(AuthorEntity author);
    BookEntity findByTitleAndPublisher(String title, String publisher);
}
