package com.example.tinyurl.api.response;

import com.example.tinyurl.domain.TinyUrlInfo;

public record CreateTinyUrlResponse(
        String originalUrl,
        String tinyUrlKey
) {
    public CreateTinyUrlResponse(TinyUrlInfo info) {
        this(
                info.originalUrl(),
                info.tinyUrlKey()
        );
    }
}
