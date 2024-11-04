package com.example.tinyurl.domain;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TinyUrlRepository {
    TinyUrl save(TinyUrl tinyUrl);
    Boolean existsByTinyUrlKey(String urlKey);
    TinyUrl findByTinyUrlKey(String tinyUrlKey);
    void deleteAll();
    List<TinyUrl> findAll();
}
