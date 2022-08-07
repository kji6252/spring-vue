package io.github.kji6252.springvue.service;

import io.github.kji6252.springvue.domain.HotKeyword;
import io.github.kji6252.springvue.repository.HotKeywordRepository;
import io.github.kji6252.springvue.service.dto.QueryCountEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class HotKeywordServiceImpl implements HotKeywordService {

    public static final PageRequest ORDER_BY_SEARCH_COUNT_DESC = PageRequest.of(0, 10, Sort.Direction.DESC, "searchCount");

    private final HotKeywordRepository hotKeywordRepository;

    @Async
    @EventListener
    @Override
    public void queryCount(QueryCountEvent event) {
        HotKeyword hotKeyword = hotKeywordRepository.findById(event.getQuery()).orElse(new HotKeyword(event.getQuery()));
        hotKeyword.incrementSearchCount();
        hotKeywordRepository.save(hotKeyword);
    }

    @Cacheable(value = "top10HotKeywords", sync = true)
    @Transactional(readOnly = true)
    @Override
    public List<HotKeyword> getTop10HotKeyword() {
        return hotKeywordRepository.findAll(ORDER_BY_SEARCH_COUNT_DESC).getContent();
    }
}
