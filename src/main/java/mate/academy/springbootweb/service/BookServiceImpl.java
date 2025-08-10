package mate.academy.springbootweb.service;

import lombok.RequiredArgsConstructor;
import mate.academy.springbootweb.dto.BookDto;
import mate.academy.springbootweb.dto.CreateBookRequestDto;
import mate.academy.springbootweb.exception.EntityNotFoundException;
import mate.academy.springbootweb.mapper.BookMapper;
import mate.academy.springbootweb.model.Book;
import mate.academy.springbootweb.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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
    @Transactional
    public BookDto create(CreateBookRequestDto dto) {
        Book book = mapper.toModel(dto);
        repository.save(book);
        return mapper.toDto(book);
    }
}
