package io.github.kji6252.springvue.remote.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.time.ZonedDateTime;
import java.util.List;

@Value
public class KakaoBlogResultDTO {
    Meta meta;

    List<Document> documents;

    public int getTotalCount() {
        return meta.getPageableCount();
    }

    @Value
    public static class Meta {

        @JsonProperty("pageable_count")
        int pageableCount;

        @JsonProperty("is_end")
        boolean end;
    }

    @Value
    public static class Document {

        String title;
        String contents;
        String url;
        @JsonProperty("blogname")
        String blogName;
        String thumbnail;
        ZonedDateTime datetime;
    }
}
