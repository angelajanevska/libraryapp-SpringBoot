package mk.ukim.finki.libraryapp.domain.model.dto;

import lombok.Data;

@Data
public class BookDto {
    String name;
    String category;
    Long author;
    Integer availableCopies;

    public BookDto(String name, String category, Long author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
    public BookDto(){}
}
