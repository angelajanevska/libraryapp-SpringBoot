package mk.ukim.finki.libraryapp.service;

import mk.ukim.finki.libraryapp.domain.model.Author;
import mk.ukim.finki.libraryapp.domain.model.Country;

public interface AuthorService {
    void create(String name, String surname, Long countryID);
    Author findById(Long id);
}
