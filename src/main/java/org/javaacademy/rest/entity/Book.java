package org.javaacademy.rest.entity;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class Book {
    private String id;

    @NonNull
    private String name;
    @NonNull
    private List<BookPage> bookPages;
    @NonNull
    private  String author;
    @NonNull
    private Integer price;
}
