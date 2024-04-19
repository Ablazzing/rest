package org.javaacademy.rest.repository;

import lombok.SneakyThrows;
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
        String uuid = UUID.randomUUID().toString()
                .substring(0, 5);
        book.setId(uuid);
        bookMap.put(uuid, book);
        return book;
    }

    /**
     * Get all (READ)
     */
    @SneakyThrows
    public List<Book> findAll() {
        Thread.sleep(2000);
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
