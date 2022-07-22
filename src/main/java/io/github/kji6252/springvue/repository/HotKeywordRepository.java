package io.github.kji6252.springvue.repository;

import io.github.kji6252.springvue.domain.HotKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotKeywordRepository extends JpaRepository<HotKeyword, String> {
}
