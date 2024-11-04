package com.example.tinyurl.domain;

public record TinyUrlInfo(
        Long id,
        String originalUrl,
        String tinyUrlKey,
        Long redirectCount
) {
    public TinyUrlInfo(TinyUrl tinyUrl) {
        this(
                tinyUrl.getId(),
                tinyUrl.getOriginalUrl(),
                tinyUrl.getTinyUrlKey(),
                tinyUrl.getRedirectCount()
        );
    }
}
