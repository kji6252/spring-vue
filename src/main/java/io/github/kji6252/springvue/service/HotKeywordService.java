package io.github.kji6252.springvue.service;

import io.github.kji6252.springvue.domain.HotKeyword;

import java.util.List;

public interface HotKeywordService {
    void queryCount(String query);

    List<HotKeyword> getTop10HotKeyword();
}
