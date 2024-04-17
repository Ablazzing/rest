package org.javaacademy.rest.service;

import lombok.RequiredArgsConstructor;
import org.javaacademy.rest.dto.BookDtoRq;
import org.javaacademy.rest.dto.BookDtoRs;
import org.javaacademy.rest.entity.Book;
import org.javaacademy.rest.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public BookDtoRs create(BookDtoRq dto) {
        Book newBook = convertToEntity(dto);
        Book createdBook = bookRepository.add(newBook);
        return convertToDtoRs(createdBook, LocalDateTime.now());
    }

    public List<BookDtoRs> getAll() {
        return bookRepository.findAll().stream()
                .map(this::convertToDtoRs)
                .toList();
    }

    public BookDtoRs getByKey(String key) {
        return bookRepository.findByKey(key)
                .map(this::convertToDtoRs)
                .orElseThrow();
    }

    public void update(String key, BookDtoRq dtoRq) {
        bookRepository.updateByKey(key, convertToEntity(dtoRq));
    }

    public BookDtoRs patch(String key, BookDtoRq updateDto) {
        Book oldBook = bookRepository.findByKey(key).orElseThrow();
        oldBook.setName(updateDto.getName() != null ? updateDto.getName() : oldBook.getName());
        oldBook.setAuthor(updateDto.getAuthor() != null ? updateDto.getAuthor() : oldBook.getAuthor());
        oldBook.setPrice(updateDto.getPrice() != null ? updateDto.getPrice() : oldBook.getPrice());
        oldBook.setPages(updateDto.getPages() != null ? updateDto.getPages() : oldBook.getPages());
        bookRepository.updateByKey(key, oldBook);
        return convertToDtoRs(oldBook);
    }

    public boolean deleteByKey(String key) {
        return bookRepository.deleteByKey(key);
    }

    private BookDtoRs convertToDtoRs(Book book) {
        return convertToDtoRs(book, null);
    }

    private Book convertToEntity(BookDtoRq dto) {
        return new Book(dto.getName(), dto.getPages(), dto.getAuthor(), dto.getPrice());
    }

    private BookDtoRs convertToDtoRs(Book book, LocalDateTime timeApply) {
        return new BookDtoRs(
                book.getName(),
                book.getPages(),
                book.getAuthor(),
                book.getPrice(),
                timeApply,
                book.getId()
        );
    }

}
