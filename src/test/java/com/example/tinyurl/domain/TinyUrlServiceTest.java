package com.example.tinyurl.domain;

import com.example.tinyurl.api.request.CreateTinyUrlRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class TinyUrlServiceTest {

    @Autowired
    private TinyUrlService tinyUrlService;

    @Autowired
    private TinyUrlRepository tinyUrlRepository;

    @AfterEach
    void tearDown() {
        tinyUrlRepository.deleteAll();
    }

    @Test
    void 단축_URL을_생성한다() {
        // given
        String url = "https://questrips.com";
        TinyUrlInfo info = tinyUrlService.createTinyUrl(url);

        // when
        List<TinyUrl> urls = tinyUrlRepository.findAll();

        // then
        assertThat(urls.size()).isOne();
    }

    @Test
    void 단축_URL로_조회하면_원래_URL이_조회되어야_한다() {
        // given
        String expectOriginUrl = "https://questrips.com";
        String urlKey = tinyUrlService.createTinyUrl(expectOriginUrl).tinyUrlKey();

        // when
        TinyUrlInfo info = tinyUrlService.getOriginalUrlByTinyUrl(urlKey);

        // then
        assertThat(info.originalUrl()).isEqualTo(expectOriginUrl);
    }

    @Test
    void 단축_URL을_조회하면_조회수가_1_증가한다() {
        // given
        String expectOriginUrl = "https://questrips.com";
        String urlKey = tinyUrlService.createTinyUrl(expectOriginUrl).tinyUrlKey();

        // when
        TinyUrlInfo info = tinyUrlService.getOriginalUrlByTinyUrl(urlKey);

        // then
        assertThat(info.redirectCount()).isOne();
    }
}