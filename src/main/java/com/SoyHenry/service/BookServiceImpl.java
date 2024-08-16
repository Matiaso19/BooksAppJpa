package com.SoyHenry.service;

import com.SoyHenry.dto.BookDtoRequest;
import com.SoyHenry.dto.BookDtoResponse;
import com.SoyHenry.entities.Book;
import com.SoyHenry.mapper.BookMapper;
import com.SoyHenry.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookMapper bookMapper;

    @Override
    public List<BookDtoResponse> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return mapToDtoList(books);
    }

    @Override
    public void createBook(BookDtoRequest bookDtoRequest) {
        Book book = bookMapper.mapToEntity(bookDtoRequest);
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {

        bookRepository.deleteById(id);
    }

    @Override
    public BookDtoResponse getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(bookMapper::mapToDtoResponse).orElse(null);
    }

    @Override
    public void updateBook(Long id, BookDtoRequest bookDtoRequest) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if(optionalBook.isPresent()){
        Book book = optionalBook.get();
        book.setAuthor(bookDtoRequest.getAuthor());
        book.setPageQuantity(bookDtoRequest.getPageQuantity());
        book.setPublishYear(bookDtoRequest.getPublishYear());
        book.setTitle(bookDtoRequest.getTitle());

        bookRepository.save(book);

        }

    }

    @Override
    public List<BookDtoResponse> FindBookByAuthor(String author) {
        List<Book> books = bookRepository.findByAuthor(author);
        return mapToDtoList(books);
    }

    @Override
    public List<BookDtoResponse> FindBookByTitle(String title) {
        List<Book> books = bookRepository.findByTitle(title);
        return mapToDtoList(books);
    }

    private List<BookDtoResponse> mapToDtoList(List<Book> books){
        return books.stream()
                .map(bookMapper::mapToDtoResponse)
                .collect(Collectors.toList());
    }
}
