package com.SoyHenry.mapper;

import com.SoyHenry.dto.BookDtoRequest;
import com.SoyHenry.dto.BookDtoResponse;
import com.SoyHenry.entities.Book;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface BookMapper {

    Book mapToEntity(BookDtoRequest bookDtoRequest);

    BookDtoResponse mapToDtoResponse(Book book);
}
