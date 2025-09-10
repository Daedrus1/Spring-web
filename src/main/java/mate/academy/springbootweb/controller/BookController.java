package mate.academy.springbootweb.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.springbootweb.dto.BookDto;
import mate.academy.springbootweb.dto.CreateBookRequestDto;
import mate.academy.springbootweb.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService service;

    @GetMapping
    public List<BookDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public BookDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto create(@RequestBody CreateBookRequestDto request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public BookDto update(@PathVariable Long id, @RequestBody BookDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
