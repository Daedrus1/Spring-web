package mate.academy.springbootweb.mapper;

import mate.academy.springbootweb.dto.BookDto;
import mate.academy.springbootweb.dto.CreateBookRequestDto;
import mate.academy.springbootweb.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDto toDto(Book book);

    @Mapping(target = "id", ignore = true)
    Book toModel(CreateBookRequestDto dto);
}
