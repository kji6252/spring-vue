package io.github.kji6252.springvue.service.dto;

import lombok.Value;

import java.time.LocalDate;

@Value
public class BlogDTO {
    String title;
    String description;
    String url;
    String blogName;
    LocalDate createdDate;
}
