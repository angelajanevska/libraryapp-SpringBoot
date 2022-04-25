package mk.ukim.finki.libraryapp.service.implementation;

import mk.ukim.finki.libraryapp.domain.model.Country;
import mk.ukim.finki.libraryapp.domain.model.exception.CountryNotFoundException;
import mk.ukim.finki.libraryapp.domain.repository.CountryRepository;
import mk.ukim.finki.libraryapp.service.CountryService;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImplementation implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImplementation(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public void create(String name, String continent) {
        this.countryRepository.save(new Country(name,continent));
    }

    @Override
    public Country findById(Long id) {
        return this.countryRepository.findById(id).orElseThrow(CountryNotFoundException::new);
    }
}
