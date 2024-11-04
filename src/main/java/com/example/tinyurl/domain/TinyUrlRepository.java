package com.example.tinyurl.domain;

import org.springframework.stereotype.Repository;

@Repository
public interface TinyUrlRepository {
    TinyUrl save(TinyUrl tinyUrl);
    Boolean existsByTinyUrlKey(String urlKey);
    TinyUrl findByTinyUrlKey(String tinyUrlKey);
}
