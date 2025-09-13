package mate.academy.springbootweb.repository;

import mate.academy.springbootweb.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
