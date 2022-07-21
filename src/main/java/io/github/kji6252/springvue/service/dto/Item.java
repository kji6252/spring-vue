package io.github.kji6252.springvue.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Value
public class Item {
    String title;
    String link;
    String description;
    @JsonProperty("bloggername")
    String bloggerName;
    @JsonProperty("bloggerlink")
    String bloggerLink;
    //yyyyMMdd
    String postdate;
}
