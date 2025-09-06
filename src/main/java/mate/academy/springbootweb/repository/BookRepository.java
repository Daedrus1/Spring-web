package mate.academy.springbootweb.repository;

import java.util.List;
import java.util.Optional;
import mate.academy.springbootweb.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByIsbn(String isbn);
}
