package org.javaacademy.rest.repository;

import org.javaacademy.rest.entity.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
public class BookRepository {
    private final Map<String, Book> bookMap = new HashMap<>();

    /**
     * Create
     */
    public Book add(Book book) {
        UUID uuid = UUID.randomUUID();
        book.setId(uuid.toString());
        bookMap.put(uuid.toString(), book);
        return book;
    }

    /**
     * Get all (READ)
     */
    public List<Book> findAll() {
        return new ArrayList<>(bookMap.values());
    }

    /**
     * Find unique book (READ)
     */
    public Optional<Book> findByKey(String key) {
        return Optional.ofNullable(bookMap.get(key));
    }

    /**
     * UPDATE
     */
    public void updateByKey(String key, Book newBook) {
        if (!bookMap.containsKey(key)) {
            throw new RuntimeException("Book is not exists");
        }
        bookMap.put(key, newBook);
    }

    /**
     * Delete (DELETE)
     */
    public boolean deleteByKey(String key) {
        return bookMap.remove(key) != null;
    }

}
