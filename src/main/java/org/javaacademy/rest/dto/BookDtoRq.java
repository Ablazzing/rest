package org.javaacademy.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.javaacademy.rest.entity.Page;

import java.util.List;

@Data
@NoArgsConstructor
public class BookDtoRq {
    private String name;
    private List<Page> pages;
    private String author;
    private Integer price;
}
