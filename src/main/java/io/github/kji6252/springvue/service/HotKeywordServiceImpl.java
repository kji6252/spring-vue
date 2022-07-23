package io.github.kji6252.springvue.service;

import io.github.kji6252.springvue.domain.HotKeyword;
import io.github.kji6252.springvue.repository.HotKeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class HotKeywordServiceImpl implements HotKeywordService {

    public static final PageRequest ORDER_BY_SEARCH_COUNT_DESC = PageRequest.of(0, 10, Sort.Direction.DESC, "searchCount");

    private final HotKeywordRepository hotKeywordRepository;

    @Override
    public void queryCount(String query) {
        HotKeyword hotKeyword = hotKeywordRepository.findById(query).orElse(new HotKeyword(query));
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
