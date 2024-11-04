package com.example.tinyurl.domain;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TinyUrlReader {

    private final TinyUrlRepository tinyUrlRepository;

    @Transactional
    public TinyUrl read(String urlKey) {
        TinyUrl url = tinyUrlRepository.findByTinyUrlKey(urlKey);
        url.incrementRedirectCount();

        return url;
    }
}
