package mate.academy.springbootweb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "books", uniqueConstraints = @UniqueConstraint(
        name = "uk_books_isbn",
        columnNames = "isbn"
        ))
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 255)
    private String title;
    @Column(nullable = false, length = 255)
    private String author;
    @Column(nullable = false, length = 20)
    private String isbn;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(length = 512)
    private String coverImage;

}
