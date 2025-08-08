package mate.academy.springbootweb.controller;

import lombok.RequiredArgsConstructor;
import mate.academy.springbootweb.dto.BookDto;
import mate.academy.springbootweb.dto.CreateBookRequestDto;
import mate.academy.springbootweb.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books") // с context-path /api получим /api/books
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
    public BookDto create(@RequestBody CreateBookRequestDto request) {
        return service.create(request);
    }
}
