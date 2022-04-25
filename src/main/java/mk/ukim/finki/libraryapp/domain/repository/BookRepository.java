package mk.ukim.finki.libraryapp.domain.repository;

import mk.ukim.finki.libraryapp.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
