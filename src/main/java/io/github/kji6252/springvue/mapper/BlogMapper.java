package io.github.kji6252.springvue.mapper;

import io.github.kji6252.springvue.remote.dto.*;
import io.github.kji6252.springvue.service.dto.Blog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper
public interface BlogMapper {

    BlogMapper INSTANCE = Mappers.getMapper(BlogMapper.class);

    @Named("itemsToDocument")
    @Mapping(source = "description", target = "contents")
    @Mapping(source = "link", target = "url")
    @Mapping(source = "bloggerName", target = "blogName")
    @Mapping(target = "datetime", expression = "java(item.getPostdate().atStartOfDay(java.time.ZoneId.systemDefault()))")
    @Mapping(target = "thumbnail", ignore = true)
    KakaoBlogResultDTO.Document itemToDocument(NaverBlogResultDTO.Item item);

    @Mapping(source = "total", target = "meta.pageableCount")
    @Mapping(source = "items", target = "documents", qualifiedByName = "itemsToDocument")
    KakaoBlogResultDTO naverToKakao(NaverBlogResultDTO naverBlogResultDTO);

    @Mapping(source = "contents", target = "description")
    @Mapping(source = "datetime", target = "createdDate")
    Blog documentToBlog(KakaoBlogResultDTO.Document document);

    List<Blog> documentToBlog(Collection<KakaoBlogResultDTO.Document> document);


}
