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
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "books", uniqueConstraints = @UniqueConstraint(
        name = "uk_books_isbn",
        columnNames = "isbn"
        ))
@Getter
@Setter
@SQLDelete(sql = "UPDATE books SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
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
    @Column(nullable = false)
    private boolean deleted = false;

}
