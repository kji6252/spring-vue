package io.github.kji6252.springvue.service;

import io.github.kji6252.springvue.domain.Blog;
import io.github.kji6252.springvue.domain.FavoriteBlog;
import io.github.kji6252.springvue.domain.FavoriteBlogID;
import io.github.kji6252.springvue.mapper.BlogMapper;
import io.github.kji6252.springvue.repository.FavoriteBlogRepository;
import io.github.kji6252.springvue.service.dto.BlogDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class FavoriteBlogServiceImpl implements FavoriteBlogService {

    private final FavoriteBlogRepository favoriteBlogRepository;

    @Override
    public void registerFavoriteBlog(BlogDTO blogDTO, String userName) {
        FavoriteBlogID favoriteBlogID = FavoriteBlogID.of(userName, blogDTO.hashCode());
        if (favoriteBlogRepository.existsById(favoriteBlogID)) {
            throw new FavoriteBlogServiceException("Duplicate Blog");
        }
        Blog blog = BlogMapper.INSTANCE.dtoToDomain(blogDTO);
        favoriteBlogRepository.save(FavoriteBlog.of(favoriteBlogID,
                                                    blog,
                                                    LocalDateTime.now()));
    }

    @Override
    public void removeFavoriteBlog(String userName, int blogHashCode) {
        FavoriteBlogID favoriteBlogID = FavoriteBlogID.of(userName, blogHashCode);
        if (!favoriteBlogRepository.existsById(favoriteBlogID)) {
            throw new FavoriteBlogServiceException("Remove Blog Not Found");
        }
        favoriteBlogRepository.deleteById(favoriteBlogID);
    }

    @Override
    public Page<FavoriteBlog> getFavoriteBlogs(Pageable pageable, String userName) {
        return favoriteBlogRepository.findAllByUserName(pageable, userName);
    }

    static class FavoriteBlogServiceException extends AbstractThrowableProblem {

        public static final URI TYPE = URI.create("/");

        protected FavoriteBlogServiceException(String detail) {
            super(TYPE, "FavoriteBlogService Error", Status.BAD_REQUEST, detail);
        }
    }
}
