package org.javaacademy.rest.controller;

import lombok.RequiredArgsConstructor;
import org.javaacademy.rest.dto.BookDtoRq;
import org.javaacademy.rest.dto.BookDtoRs;
import org.javaacademy.rest.dto.BookPageDtoRs;
import org.javaacademy.rest.dto.PageDto;
import org.javaacademy.rest.service.BookService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
@CacheConfig(cacheNames = "findAll")
public class BookController {
    private final BookService bookService;

    //GET http://localhost:8080/book - получение всех книг
    @GetMapping
    @Cacheable(cacheNames = "findAll")
    @CachePut(cacheNames = "findAll", condition = "#refresh==true")
    public List<BookDtoRs> getAllBooks(@RequestParam(required = false) boolean refresh) {
        List<BookDtoRs> result = bookService.getAll();
        return result;
    }

    //GET http://localhost:8080/book/key - получение книги
    @GetMapping("/{key}")
    public BookDtoRs getBookByKey(@PathVariable String key) {
        return bookService.getByKey(key);
    }

    //POST http://localhost:8080/book - создание новой книги
    @PostMapping
    @CacheEvict(cacheNames = "findAll", allEntries = true)
    public ResponseEntity<BookDtoRs> createBook(@RequestBody BookDtoRq dto) {
        return ResponseEntity.status(CREATED).body(bookService.create(dto));
    }

    //PUT http://localhost:8080/book/key - обновление книги (все поля в dto заполнены)
    @PutMapping("/{key}")
    public ResponseEntity<?> updateBook(@PathVariable String key,@RequestBody BookDtoRq dto) {
        bookService.update(key, dto);
        return ResponseEntity.status(ACCEPTED).build();
    }

    //DELETE http://localhost:8080/book/key - удаление книги
    @DeleteMapping("/{key}")
    public ResponseEntity<?> deleteBook(@PathVariable String key) {
        boolean result = bookService.deleteByKey(key);
        return  result
                ? ResponseEntity.status(ACCEPTED).build()
                : ResponseEntity.status(NOT_FOUND).build();
    }

    //PATCH http://localhost:8080/book - обновление одного или нескольких полей книги
    @PatchMapping("/{key}")
    public ResponseEntity<BookDtoRs> patchBook(@PathVariable String key,@RequestBody BookDtoRq dto) {
        return ResponseEntity.status(ACCEPTED).body(bookService.patch(key, dto));
    }

    //GET http://localhost:8080/book/key/page - Получение страниц для конкретной книги
    @GetMapping("/{bookKey}/page")
    public PageDto<List<BookPageDtoRs>> getPages(@PathVariable String bookKey,
                                                 @RequestParam Integer startElement,
                                                 @RequestParam Integer pageSize) {
        return bookService.getPages(bookKey, startElement, pageSize);
    }

}
