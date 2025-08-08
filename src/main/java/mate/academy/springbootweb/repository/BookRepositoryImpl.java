package mate.academy.springbootweb.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lombok.RequiredArgsConstructor;
import mate.academy.springbootweb.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {
    private final EntityManagerFactory entityManagerFactory;

    @Override
    public Book save(Book book) {
        EntityTransaction tx = null;
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            tx = em.getTransaction();
            tx.begin();

            if (book.getId() == null) {
                em.persist(book);        // INSERT
            } else {
                book = em.merge(book);   // UPDATE
            }

            tx.commit();
            return book;
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    @Override
    public List<Book> findAll() {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            return em.createQuery("SELECT b FROM Book b", Book.class)
                    .getResultList();
        }
    }

    @Override
    public Optional<Book> findById(Long id) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            return Optional.ofNullable(em.find(Book.class, id));
        }
    }
}
