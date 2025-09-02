package mate.academy.springbootweb.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.PersistenceUnit;
import mate.academy.springbootweb.exception.DataProcessingException;
import mate.academy.springbootweb.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class BookRepositoryImpl implements BookRepository {

    @PersistenceUnit
    private final EntityManagerFactory entityManagerFactory;

    public BookRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Book save(Book book) {
        EntityTransaction tx = null;
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            tx = em.getTransaction();
            tx.begin();

            Book managed;
            if (book.getId() == null) {
                em.persist(book);
                managed = book;
            } else {
                managed = em.merge(book);
            }

            tx.commit();
            return managed;

        } catch (RuntimeException ex) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new DataProcessingException(
                    "Failed to save Book (id=" + book.getId() + ", isbn=" + book.getIsbn() + ")",
                    ex
            );
        }
    }


    @Override
    public Optional<Book> findById(Long id) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            Book book = em.find(Book.class, id);
            return Optional.ofNullable(book);
        } catch (PersistenceException e) {
            throw new DataProcessingException("Unable to find book with id " + id, e);
        }
    }

    @Override
    public List<Book> findAll() {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            return em.createQuery("SELECT b FROM Book b", Book.class)
                    .getResultList();
        } catch (PersistenceException e) {
            throw new DataProcessingException("Unable to retrieve books", e);
        }
    }
}
