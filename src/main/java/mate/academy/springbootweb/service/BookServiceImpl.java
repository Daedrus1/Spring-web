package mate.academy.springbootweb.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.springbootweb.dto.BookDto;
import mate.academy.springbootweb.dto.CreateBookRequestDto;
import mate.academy.springbootweb.exception.EntityNotFoundException;
import mate.academy.springbootweb.mapper.BookMapper;
import mate.academy.springbootweb.model.Book;
import mate.academy.springbootweb.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {
    private final BookRepository repository;
    private final BookMapper mapper;

    @Override
    public List<BookDto> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public BookDto getById(Long id) {
        Book book = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found: id=" + id));
        return mapper.toDto(book);
    }

    @Override
    public BookDto create(CreateBookRequestDto dto) {
        Book book = mapper.toModel(dto);
        repository.save(book);
        return mapper.toDto(book);
    }

    @Override
    public BookDto update(Long id, BookDto update) {
        Book current = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book " + id + " not found"));

        // Полный PUT: перезаписываем все поля значениями из DTO
        current.setTitle(update.getTitle());
        current.setAuthor(update.getAuthor());
        current.setIsbn(update.getIsbn());
        current.setPrice(update.getPrice());
        current.setDescription(update.getDescription());
        current.setCoverImage(update.getCoverImage());

        Book saved = repository.save(current);
        return mapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Book " + id + " not found");
        }
        repository.deleteById(id);
    }
}

