package mk.ukim.finki.libraryapp.domain.data;

import mk.ukim.finki.libraryapp.service.AuthorService;
import mk.ukim.finki.libraryapp.service.BookService;
import mk.ukim.finki.libraryapp.service.CountryService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInit {
    private final BookService bookService;
    private final AuthorService authorService;
    private final CountryService countryService;

    public DataInit(BookService bookService, AuthorService authorService, CountryService countryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @PostConstruct
    public void init(){
        this.countryService.create("England","Europe");
        this.countryService.create("United States","North America");
        this.countryService.create("Italy","Europe");

        this.authorService.create("William","Shakespeare",1L);
        this.authorService.create("Stephen","King",2L);
        this.authorService.create("Elena","Ferrante",3L);

        this.bookService.create("Hamlet","DRAMA",1L,5);
        this.bookService.create("The Green Mile","FANTASY",2L,2);
        this.bookService.create("My Brilliant Friend","NOVEL",3L,4);
    }
}
