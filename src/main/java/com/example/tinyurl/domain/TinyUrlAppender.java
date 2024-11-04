package com.example.tinyurl.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TinyUrlAppender {

    private final UrlKeyGenerator urlKeyGenerator;
    private final TinyUrlRepository tinyUrlRepository;

    public TinyUrl append(String originalUrl) {
        String urlKey = urlKeyGenerator.getUniqueTinyUrlKey();
        TinyUrl tinyUrl = TinyUrl.of(originalUrl, urlKey);

        return tinyUrlRepository.save(tinyUrl);
    }
}
