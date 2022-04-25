package mk.ukim.finki.libraryapp.service.implementation;

import mk.ukim.finki.libraryapp.domain.model.Author;
import mk.ukim.finki.libraryapp.domain.model.Book;
import mk.ukim.finki.libraryapp.domain.model.Category;
import mk.ukim.finki.libraryapp.domain.model.exception.AuthorDoesNotExistException;
import mk.ukim.finki.libraryapp.domain.model.exception.BookDoesNotExistException;
import mk.ukim.finki.libraryapp.domain.repository.AuthorRepository;
import mk.ukim.finki.libraryapp.domain.repository.BookRepository;
import mk.ukim.finki.libraryapp.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImplementation implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImplementation(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void create(String name, String category, Long authorID, Integer availableCopies) {
        Author bookAuthor = this.authorRepository.findById(authorID).orElseThrow(AuthorDoesNotExistException::new);
        this.bookRepository.save(new Book(name,Category.valueOf(category),bookAuthor,availableCopies));
    }

    @Override
    public void delete(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(BookDoesNotExistException::new);
        this.bookRepository.delete(book);
    }

    @Override
    public void update(Long id, String name, String category, Long authorID, Integer availableCopies) {
        Book book = this.bookRepository.findById(id).orElseThrow(BookDoesNotExistException::new);
        book.setName(name);
        book.setAuthor(this.authorRepository.findById(authorID).orElseThrow(AuthorDoesNotExistException::new));
        book.setCategory(Category.valueOf(category));
        book.setAvailableCopies(availableCopies);
        this.bookRepository.save(book);
    }

    @Override
    public Book findById(Long id) {
        return this.bookRepository.findById(id).orElseThrow(BookDoesNotExistException::new);
    }
}
