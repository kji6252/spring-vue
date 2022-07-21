package io.github.kji6252.springvue.mapper;

import io.github.kji6252.springvue.service.dto.Document;
import io.github.kji6252.springvue.service.dto.KakaoBlogResultDTO;
import io.github.kji6252.springvue.service.dto.NaverBlogResultDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface BlogMapper {

    BlogMapper INSTANCE = Mappers.getMapper(BlogMapper.class);


    default KakaoBlogResultDTO naverToKakao(NaverBlogResultDTO naverBlogResultDTO) {
        List<Document> collect =
                naverBlogResultDTO.getItems()
                                  .stream()
                                  .map(item -> new Document(item.getTitle(),
                                                            item.getDescription(),
                                                            item.getLink(),
                                                            item.getBloggerName(),
                                                            "",
                                                            ZonedDateTime.now()))
                                  .collect(Collectors.toList());

        return new KakaoBlogResultDTO(null, collect);
    }
}
