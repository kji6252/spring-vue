package io.github.kji6252.springvue.controller.vm;


import lombok.Value;

import java.time.LocalDate;

@Value
public class FavoriteBlogVM {
    int blogHashCode;
    String title;
    String description;
    String url;
    String blogName;
    LocalDate createdDate;
}
