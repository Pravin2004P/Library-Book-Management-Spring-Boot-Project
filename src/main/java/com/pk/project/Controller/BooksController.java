package com.pk.project.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pk.project.Entities.Book;
import com.pk.project.Service.BookService;
@RestController
@RequestMapping("/api")

public class BooksController {

    
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
       List <Book> books = this.bookService.getAllBooks();
       if(books.isEmpty()) {
           return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
       }
        return ResponseEntity.ok(books);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
        Book book = this.bookService.getBookById(id);
        if(book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(book);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") int id, @RequestBody Book book) {
        Book updatedBook = this.bookService.updateBook(id, book);
        if (updatedBook == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updatedBook);
    }
    @PostMapping(value = "/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book savedBook = this.bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @PostMapping(value = "/books/bulk")
    public ResponseEntity<List<Book>> addBooks(@RequestBody List<Book> books) {
        List<Book> savedBooks = this.bookService.addBooks(books);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBooks);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable("id") int id) {
        if (this.bookService.deleteBookById(id) == false) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.noContent().build();
    }
    
        @DeleteMapping("/books")
    public ResponseEntity<Void> deleteAllBooks() {
     if(   this.bookService.deleteAllBooks()==true) {
        return ResponseEntity.ok().build();
     } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
     }
        
    }
    
}
