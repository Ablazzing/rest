package org.javaacademy.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.javaacademy.rest.entity.BookPage;

import java.util.List;

@Data
@NoArgsConstructor
public class BookDtoRq {
    private String name;
    private List<BookPage> bookPages;
    private String author;
    private Integer price;
}
