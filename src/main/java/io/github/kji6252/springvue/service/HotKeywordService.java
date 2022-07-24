package io.github.kji6252.springvue.service;

import io.github.kji6252.springvue.domain.HotKeyword;
import io.github.kji6252.springvue.service.dto.QueryCountEvent;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;

public interface HotKeywordService {
    void queryCount(QueryCountEvent event);

    List<HotKeyword> getTop10HotKeyword();
}
