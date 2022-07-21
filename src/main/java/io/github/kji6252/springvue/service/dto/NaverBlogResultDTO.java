package io.github.kji6252.springvue.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;

import java.time.ZonedDateTime;
import java.util.Collection;

@Value
public class NaverBlogResultDTO {

    //Fri, 22 Jul 2022 01:24:45 +09:00
//    @JsonFormat(pattern = "E, dd M yyyy HH:mm:ss Z")
    String lastBuildDate;
    int total;
    int start;
    int display;
    Collection<Item> items;
}
