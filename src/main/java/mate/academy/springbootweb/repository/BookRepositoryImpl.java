package mate.academy.springbootweb.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import mate.academy.springbootweb.exception.DataProcessingException;
import mate.academy.springbootweb.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Book save(Book book) {
        try {
            if (book.getId() == null) {
                em.persist(book);
                return book;
            }
            return em.merge(book);
        } catch (RuntimeException ex) {
            throw new DataProcessingException(
                    "Failed to save Book (id=" + book.getId() + ", isbn=" + book.getIsbn() + ")", ex
            );
        }
    }

    @Override
    public List<Book> findAll() {
        try {
            return em.createQuery("SELECT b FROM Book b", Book.class)
                    .getResultList();
        } catch (RuntimeException ex) {
            throw new DataProcessingException("Failed to find all Books", ex);
        }
    }

    @Override
    public Optional<Book> findById(Long id) {
        try {
            return Optional.ofNullable(em.find(Book.class, id));
        } catch (RuntimeException ex) {
            throw new DataProcessingException("Failed to find Book by id=" + id, ex);
        }
    }
}
