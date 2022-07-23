package io.github.kji6252.springvue.repository;

import io.github.kji6252.springvue.domain.FavoriteBlog;
import io.github.kji6252.springvue.domain.FavoriteBlogID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteBlogRepository extends JpaRepository<FavoriteBlog, FavoriteBlogID> {

    @Query("SELECT fb FROM FavoriteBlog fb WHERE fb.favoriteBlogID.userName = :userName")
    Page<FavoriteBlog> findAllByUserName(Pageable pageable, String userName);
}
