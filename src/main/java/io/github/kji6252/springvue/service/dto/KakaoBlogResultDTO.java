package io.github.kji6252.springvue.service.dto;

import lombok.Value;

import java.util.Collection;

@Value
public class KakaoBlogResultDTO {
    Meta meta;

    Collection<Document> documents;
}
