package com.SoyHenry.service;

import com.SoyHenry.dto.BookDtoRequest;
import com.SoyHenry.dto.BookDtoResponse;
import com.SoyHenry.entities.Book;

import java.util.List;

public interface BookService {

    List<BookDtoResponse> getAllBooks();
    void createBook(BookDtoRequest bookDtoRequest);
    void deleteBook(Long id);
    BookDtoResponse getBookById(Long id);
    void updateBook(Long id, BookDtoRequest bookDtoRequest);
    List<BookDtoResponse> FindBookByAuthor(String author);
    List<BookDtoResponse> FindBookByTitle(String title);



}
