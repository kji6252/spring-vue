package io.github.kji6252.springvue.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class Meta {

    @JsonProperty("total_count")
    int totalCount;
    @JsonProperty("pageable_count")
    int pageableCount;
    @JsonProperty("is_end")
    boolean isEnd;
}
