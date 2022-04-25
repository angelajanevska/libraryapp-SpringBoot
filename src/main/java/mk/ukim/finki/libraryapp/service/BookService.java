package mk.ukim.finki.libraryapp.service;

import mk.ukim.finki.libraryapp.domain.model.Book;
import mk.ukim.finki.libraryapp.domain.model.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> create(String name, String category, Long authorID, Integer availableCopies);
    Optional<Book> create(BookDto bookDto);
    Optional<Book> delete (Long id);
    Optional<Book> update(Long id, String name, String category, Long authorID, Integer availableCopies);
    Optional<Book> update(Long id, BookDto bookDto);
    Optional<Book> findById(Long id);
    List<Book> findAll();
    Page<Book> findAllWithPagination(Pageable pageable);
}
