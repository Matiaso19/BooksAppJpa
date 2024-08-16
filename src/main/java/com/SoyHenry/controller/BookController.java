package com.SoyHenry.controller;


import com.SoyHenry.dto.BookDtoRequest;
import com.SoyHenry.dto.BookDtoResponse;
import com.SoyHenry.entities.Book;
import com.SoyHenry.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/books")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDtoResponse>> getAllBooks(){
        try{
            List<BookDtoResponse> books =  bookService.getAllBooks();
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDtoResponse> getBookById(@PathVariable Long id){
        try {
            BookDtoResponse book = bookService.getBookById(id);
            if(book != null){
                return new ResponseEntity<>(book, HttpStatus.OK);
            } else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<String> createBook(@RequestBody BookDtoRequest bookDtoRequest){
        try {
            bookService.createBook(bookDtoRequest);
            return new ResponseEntity<>("Libro creado correctamente", HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>("Hubo un error al crear el libro: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id){
        try{
            BookDtoResponse deletedBook = bookService.getBookById(id);
            if(deletedBook != null){
                bookService.deleteBook(id);
                return new ResponseEntity<>("Libro con id: " + id + "borrado exitosamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No se encontro el libro con id: " + id, HttpStatus.NOT_FOUND);

            }
        } catch (Exception e){
            return new ResponseEntity<>("Hubo un error al intentar elminar el libro con id: " + id, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable Long id, @RequestBody BookDtoRequest bookDtoRequest){
        try{
            BookDtoResponse updatedBook = bookService.getBookById(id);
            if(updatedBook != null){
                bookService.updateBook(id, bookDtoRequest);
                return new ResponseEntity<>("Libro con id: " + id + "modificado exitosamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No se encontro el libro con id: " + id, HttpStatus.NOT_FOUND);

            }
        } catch (Exception e){
            return new ResponseEntity<>("Hubo un error al intentar modificar el libro con id: " + id, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/search/title/{title}")
    public ResponseEntity<List<BookDtoResponse>> getBookByTitle(@PathVariable String title){
        try {
            List<BookDtoResponse> bookList = bookService.FindBookByTitle(title);
            return new ResponseEntity<>(bookList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/author/{author}")
    public ResponseEntity<List<BookDtoResponse>> getBookByAuthor(@PathVariable String author){
        try {
            List<BookDtoResponse> bookList = bookService.FindBookByAuthor(author);
            return new ResponseEntity<>(bookList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
