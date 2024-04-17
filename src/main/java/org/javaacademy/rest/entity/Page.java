package org.javaacademy.rest.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class Page {
    @NonNull
    private String text;
}
