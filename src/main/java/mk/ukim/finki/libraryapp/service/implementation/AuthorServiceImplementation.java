package mk.ukim.finki.libraryapp.service.implementation;

import mk.ukim.finki.libraryapp.domain.model.Author;
import mk.ukim.finki.libraryapp.domain.model.Country;
import mk.ukim.finki.libraryapp.domain.model.exception.AuthorDoesNotExistException;
import mk.ukim.finki.libraryapp.domain.model.exception.CountryNotFoundException;
import mk.ukim.finki.libraryapp.domain.repository.AuthorRepository;
import mk.ukim.finki.libraryapp.domain.repository.CountryRepository;
import mk.ukim.finki.libraryapp.service.AuthorService;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImplementation implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImplementation(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public void create(String name, String surname, Long countryID) {
        Country country = this.countryRepository.findById(countryID).orElseThrow(CountryNotFoundException::new);
        this.authorRepository.save(new Author(name,surname,country));
    }

    @Override
    public Author findById(Long id) {
        return this.authorRepository.findById(id).orElseThrow(AuthorDoesNotExistException::new);
    }
}
