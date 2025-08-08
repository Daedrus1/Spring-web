package mate.academy.springbootweb.repository;

import mate.academy.springbootweb.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);
    List<Book> findAll();
    Optional<Book> findById(Long id);
}
