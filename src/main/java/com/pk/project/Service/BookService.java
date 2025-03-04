package com.pk.project.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pk.project.Dao.BookRepository;
import com.pk.project.Entities.Book;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
        return books;
    }

    public Book getBookById(int id) {
        Optional<Book> bookOpt = bookRepository.findById(id);
        return bookOpt.orElse(null);
    }

    public Book addBook(Book book) {
        try {
            return bookRepository.save(book);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save book: " + e.getMessage());
        }
    }

    public boolean deleteBookById(int id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Book updateBook(int bookId, Book book) {
        Optional<Book> oldOpt = bookRepository.findById(bookId);
        if(oldOpt.isPresent()) {
            Book oldBook = oldOpt.get();
            oldBook.setTitle(book.getTitle());
            oldBook.setAuthor(book.getAuthor());
            return bookRepository.save(oldBook);
        }
        return null;
    }
    
    public boolean deleteAllBooks() {
        try {
            bookRepository.deleteAll();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Book> addBooks(List<Book> books) {
        try {
            return (List<Book>) bookRepository.saveAll(books);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save books: " + e.getMessage());
        }
    }
}
