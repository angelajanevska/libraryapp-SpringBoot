package mk.ukim.finki.libraryapp.domain.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class BookCreatedEvent extends ApplicationEvent {

    private final LocalDateTime when;

    public BookCreatedEvent(Object source) {
        super(source);
        this.when = LocalDateTime.now();
    }

    public BookCreatedEvent(Object source, LocalDateTime when) {
        super(source);
        this.when = when;
    }
}
