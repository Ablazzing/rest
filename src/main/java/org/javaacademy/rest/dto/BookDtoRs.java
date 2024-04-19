package org.javaacademy.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.javaacademy.rest.entity.BookPage;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDtoRs {
    private String name;
    private List<BookPage> bookPages;
    private String author;
    private Integer price;
    private LocalDateTime timeApply;
    private String key;
}
