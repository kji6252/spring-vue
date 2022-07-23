package io.github.kji6252.springvue;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

public final class TestUtil {
    public static final ObjectMapper OBJECT_MAPPER = Jackson2ObjectMapperBuilder.json().build();
}
