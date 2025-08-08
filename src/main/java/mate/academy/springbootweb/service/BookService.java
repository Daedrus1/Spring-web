package mate.academy.springbootweb.service;

import mate.academy.springbootweb.dto.BookDto;
import mate.academy.springbootweb.dto.CreateBookRequestDto;
import java.util.List;

public interface BookService {
    List<BookDto> getAll();
    BookDto getById(Long id);
    BookDto create(CreateBookRequestDto dto);
}
