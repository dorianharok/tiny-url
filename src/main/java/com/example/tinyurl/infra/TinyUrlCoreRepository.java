package com.example.tinyurl.infra;

import com.example.tinyurl.domain.TinyUrl;
import com.example.tinyurl.domain.TinyUrlRepository;
import com.example.tinyurl.domain.exception.UrlKeyNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TinyUrlCoreRepository implements TinyUrlRepository {

    private final TinyUrlJpaRepository tinyUrlJpaRepository;

    @Override
    public TinyUrl save(TinyUrl tinyUrl) {
        return tinyUrlJpaRepository.save(tinyUrl);
    }

    @Override
    public Boolean existsByTinyUrlKey(String urlKey) {
        return tinyUrlJpaRepository.existsByTinyUrlKey(urlKey);
    }

    @Override
    public TinyUrl findByTinyUrlKey(String tinyUrlKey) {
        return tinyUrlJpaRepository.findByTinyUrlKey(tinyUrlKey)
                .orElseThrow(UrlKeyNotFoundException::new);
    }
}
