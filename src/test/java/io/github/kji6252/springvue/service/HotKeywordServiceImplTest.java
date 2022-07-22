package io.github.kji6252.springvue.service;

import io.github.kji6252.springvue.domain.HotKeyword;
import io.github.kji6252.springvue.repository.HotKeywordRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@MockitoSettings
class HotKeywordServiceImplTest {

    @InjectMocks
    private HotKeywordServiceImpl hotKeywordService;

    @Mock
    private HotKeywordRepository hotKeywordRepository;

    @Test
    void testHotKeywordSearchCount() {
        // given
        HotKeyword hotKeyword = new HotKeyword("나이키");
        given(hotKeywordRepository.findById(anyString())).willReturn(Optional.of(hotKeyword));
        // when
        hotKeywordService.queryCount("나이키");
        // then
        assertEquals(1, hotKeyword.getSearchCount());

        hotKeywordService.queryCount("나이키");
        assertEquals(2, hotKeyword.getSearchCount());
    }

    @Test
    void testTop10HotKeywords() {
        // given
        List<HotKeyword> hotKeywords =
                Arrays.asList(new HotKeyword("나이키"){{incrementSearchCount();}},
                              new HotKeyword("나이스") {{incrementSearchCount();incrementSearchCount();}});
        given(hotKeywordRepository.findAll((Pageable) any())).willReturn(new PageImpl<>(hotKeywords));
        // when
        List<HotKeyword> top10HotKeyword = hotKeywordService.getTop10HotKeyword();
        // then
        assertEquals("나이키", top10HotKeyword.get(0).getKeyword());
        assertEquals(1, top10HotKeyword.get(0).getSearchCount());
        assertEquals("나이스", top10HotKeyword.get(1).getKeyword());
        assertEquals(2, top10HotKeyword.get(1).getSearchCount());
    }
}