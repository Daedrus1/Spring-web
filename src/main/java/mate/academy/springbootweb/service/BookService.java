package mate.academy.springbootweb.service;

import java.util.List;
import mate.academy.springbootweb.dto.BookDto;
import mate.academy.springbootweb.dto.CreateBookRequestDto;

public interface BookService {

    List<BookDto> getAll();

    BookDto getById(Long id);

    BookDto create(CreateBookRequestDto dto);

    BookDto update(Long id, CreateBookRequestDto dto);

    void delete(Long id);

}
