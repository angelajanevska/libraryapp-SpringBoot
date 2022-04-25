package mk.ukim.finki.libraryapp.domain.repository;

import mk.ukim.finki.libraryapp.domain.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
}
