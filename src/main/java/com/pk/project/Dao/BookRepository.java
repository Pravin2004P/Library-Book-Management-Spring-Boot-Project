package com.pk.project.Dao;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.pk.project.Entities.Book;

public interface BookRepository extends CrudRepository <Book, Integer> {
    public Optional<Book> findById(int id);
}
