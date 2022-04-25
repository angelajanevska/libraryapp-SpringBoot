package mk.ukim.finki.libraryapp.service;

import mk.ukim.finki.libraryapp.domain.model.Country;

public interface CountryService {
    void create(String name, String continent);
    Country findById(Long id);
}
