package com.example.tss_project_withspring.Service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.tss_project_withspring.Data.AuthorEntity;
import com.example.tss_project_withspring.Data.AuthorRepository;
import com.example.tss_project_withspring.Data.BookEntity;
import com.example.tss_project_withspring.Data.BookRepository;
//import com.example.tss_project_withspring.Data.ReaderRepository;
//import com.example.tss_project_withspring.Data.ReadingRecordRepository;
import com.example.tss_project_withspring.DTO.BookCreationRequestDto;
import com.example.tss_project_withspring.exception.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

//    @Autowired
//    private ReaderRepository readerRepository;
//
//    @Autowired
//    private ReadingRecordRepository readingRecordRepository;

    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    public BookEntity saveBook(BookCreationRequestDto bookCreationRequestDto) {
        log.debug("Creating a new book with title {}", bookCreationRequestDto.getTitle());
        var bookEntity = mapToBookEntity(bookCreationRequestDto);
        return bookRepository.save(bookEntity);
    }

    public List<BookEntity> getBooksByAuthor(AuthorEntity authorEntity) {
        return bookRepository.findByAuthor(authorEntity);
    }

    private BookEntity mapToBookEntity(BookCreationRequestDto dto) {
        log.debug("Map bookCreationRequestDto to bookEntity");
        var bookEntity = BookEntity.builder()
                .title(dto.getTitle())
                .genre(dto.getGenre())
                .publicationDate(dto.getPublicationDate())
                .publisher(dto.getPublisher())
                .build();

        log.debug("Getting author with id '{}'", dto.getAuthorId());
        var authorEntity = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new EntityNotFoundException(dto.getAuthorId()));
        bookEntity.setAuthor(authorEntity);

        return bookEntity;
    }

//    public void deleteBookAndReadingRecords(String bookId) {
//        log.debug("Deleting book with id: {}", bookId);
//
//        var bookToDelete = bookRepository.findById(bookId)
//                .orElseThrow(() -> new EntityNotFoundException("Book not found with ID: " + bookId));
//
//        readingRecordRepository.deleteReadingRecordsByBook(bookToDelete);
//
//        bookRepository.delete(bookToDelete);
//
//        log.debug("Book and associated reading records deleted successfully");
//    }

    public List<BookEntity> getBooksByAuthor(String authorId) {
        log.debug("Getting books by author with id: '{}'", authorId);
        var authorEntity = authorRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with id: " + authorId));
        return bookRepository.findByAuthor(authorEntity);
    }

//    public List<BookEntity> getBooksByReader(String readerId) {
//        log.debug("Getting books by reader with id: '{}'", readerId);
//        var readerEntity = readerRepository.findById(readerId)
//                .orElseThrow(() -> new EntityNotFoundException("Reader not found with id: " + readerId));
//        var records = readingRecordRepository.findByReader(readerEntity);
//
//        List<BookEntity> books = new ArrayList<>();
//        records.forEach(rec -> books.add(rec.getBook()));
//
//        return books;
//    }

}

