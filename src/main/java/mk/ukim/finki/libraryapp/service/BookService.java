package mk.ukim.finki.libraryapp.service;

import mk.ukim.finki.libraryapp.domain.model.Book;

public interface BookService {
    void create(String name, String category, Long authorID, Integer availableCopies);
    void delete (Long id);
    void update(Long id, String name, String category, Long authorID, Integer availableCopies);
    Book findById(Long id);
}
