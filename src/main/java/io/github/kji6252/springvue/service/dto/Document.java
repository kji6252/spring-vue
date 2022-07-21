package io.github.kji6252.springvue.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.time.ZonedDateTime;

@Value
public class Document {

    String title;
    String contents;
    String url;
    @JsonProperty("blogname")
    String blogName;
    String thumbnail;
    ZonedDateTime datetime;
}
