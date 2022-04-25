package mk.ukim.finki.libraryapp.service.implementation;

import mk.ukim.finki.libraryapp.domain.model.Author;
import mk.ukim.finki.libraryapp.domain.model.Book;
import mk.ukim.finki.libraryapp.domain.model.Category;
import mk.ukim.finki.libraryapp.domain.model.dto.BookDto;
import mk.ukim.finki.libraryapp.domain.model.events.BookCreatedEvent;
import mk.ukim.finki.libraryapp.domain.model.exception.AuthorDoesNotExistException;
import mk.ukim.finki.libraryapp.domain.model.exception.BookDoesNotExistException;
import mk.ukim.finki.libraryapp.domain.repository.AuthorRepository;
import mk.ukim.finki.libraryapp.domain.repository.BookRepository;
import mk.ukim.finki.libraryapp.service.BookService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImplementation implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public BookServiceImplementation(BookRepository bookRepository, AuthorRepository authorRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    @Transactional
    public Optional<Book> create(String name, String category, Long authorID, Integer availableCopies) {
        Author bookAuthor = this.authorRepository.findById(authorID).orElseThrow(AuthorDoesNotExistException::new);
        Book book = this.bookRepository.save(new Book(name,Category.valueOf(category),bookAuthor,availableCopies));
        this.applicationEventPublisher.publishEvent(new BookCreatedEvent(book));
        return Optional.of(book);
    }

    @Override
    public Optional<Book> create(BookDto bookDto) {
        Author bookAuthor = this.authorRepository.findById(bookDto.getAuthor()).orElseThrow(AuthorDoesNotExistException::new);
        Book book = this.bookRepository.save(new Book(bookDto.getName(),Category.valueOf(bookDto.getCategory()),bookAuthor,bookDto.getAvailableCopies()));
        this.applicationEventPublisher.publishEvent(new BookCreatedEvent(book));
        return Optional.of(book);
    }

    @Override
    public Optional<Book> delete(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(BookDoesNotExistException::new);
        this.bookRepository.delete(book);
        return Optional.of(book);
    }

    @Override
    @Transactional
    public Optional<Book> update(Long id, String name, String category, Long authorID, Integer availableCopies) {
        Book book = this.bookRepository.findById(id).orElseThrow(BookDoesNotExistException::new);
        book.setName(name);
        book.setAuthor(this.authorRepository.findById(authorID).orElseThrow(AuthorDoesNotExistException::new));
        book.setCategory(Category.valueOf(category));
        book.setAvailableCopies(availableCopies);
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> update(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id).orElseThrow(BookDoesNotExistException::new);
        book.setName(bookDto.getName());
        book.setAuthor(this.authorRepository.findById(bookDto.getAuthor()).orElseThrow(AuthorDoesNotExistException::new));
        book.setCategory(Category.valueOf(bookDto.getCategory()));
        book.setAvailableCopies(bookDto.getAvailableCopies());
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.of(this.bookRepository.findById(id).orElseThrow(BookDoesNotExistException::new));
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Page<Book> findAllWithPagination(Pageable pageable) {
        return this.bookRepository.findAll(pageable);
    }
}
