package io.github.kji6252.springvue.repository;

import io.github.kji6252.springvue.domain.FavoriteBlog;
import io.github.kji6252.springvue.domain.FavoriteBlogID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteBlogRepository extends JpaRepository<FavoriteBlog, FavoriteBlogID> {
}
