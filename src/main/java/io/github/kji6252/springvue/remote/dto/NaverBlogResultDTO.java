package io.github.kji6252.springvue.remote.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Value
public class NaverBlogResultDTO {
    int total;
    int start;
    int display;
    List<Item> items;

    @Value
    public static class Item {
        private static final DateTimeFormatter YYYYMMDD = DateTimeFormatter.ofPattern("yyyyMMdd");
        String title;
        String link;
        String description;
        @JsonProperty("bloggername")
        String bloggerName;
        @JsonProperty("bloggerlink")
        String bloggerLink;
        LocalDate postdate;

        public Item(String title, String link, String description, String bloggerName, String bloggerLink, String postdate) {
            this.title = title;
            this.link = link;
            this.description = description;
            this.bloggerName = bloggerName;
            this.bloggerLink = bloggerLink;
            this.postdate = LocalDate.parse(postdate, YYYYMMDD);
        }
    }
}
