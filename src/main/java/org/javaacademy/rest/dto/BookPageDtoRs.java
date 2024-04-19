package org.javaacademy.rest.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class BookPageDtoRs {
    @NonNull
    private String bookName;
    @NonNull
    private String text;
}
