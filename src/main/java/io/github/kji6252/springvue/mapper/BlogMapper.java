package io.github.kji6252.springvue.mapper;

import io.github.kji6252.springvue.controller.vm.FavoriteBlogVM;
import io.github.kji6252.springvue.domain.Blog;
import io.github.kji6252.springvue.domain.FavoriteBlog;
import io.github.kji6252.springvue.remote.dto.*;
import io.github.kji6252.springvue.service.dto.BlogDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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
    BlogDTO documentToBlog(KakaoBlogResultDTO.Document document);

    List<BlogDTO> documentToBlog(Collection<KakaoBlogResultDTO.Document> document);

    Blog dtoToDomain(BlogDTO blogDTO);

    @Mapping(source = "favoriteBlogID.blogHashCode", target = "blogHashCode")
    @Mapping(source = "blog.title", target = "title")
    @Mapping(source = "blog.description", target = "description")
    @Mapping(source = "blog.url", target = "url")
    @Mapping(source = "blog.blogName", target = "blogName")
    @Mapping(source = "blog.createdDate", target = "createdDate")
    FavoriteBlogVM entityToVM(FavoriteBlog favoriteBlog);
    List<FavoriteBlogVM> entityToVM(List<FavoriteBlog> favoriteBlogs);
}
