package io.github.kji6252.springvue.service.dto;

import lombok.Value;

@Value(staticConstructor = "of")
public class QueryCountEvent {
    String query;
}
