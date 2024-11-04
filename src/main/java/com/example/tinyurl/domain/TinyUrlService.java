package com.example.tinyurl.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TinyUrlService {

    private final TinyUrlAppender tinyUrlAppender;
    private final TinyUrlReader tinyUrlReader;

    public TinyUrlInfo createTinyUrl(String originalUrl) {
        TinyUrl appended = tinyUrlAppender.append(originalUrl);

        return new TinyUrlInfo(appended);
    }

    public TinyUrlInfo getOriginalUrlByTinyUrl(String tinyUrl) {
        TinyUrl url = tinyUrlReader.read(tinyUrl);

        return new TinyUrlInfo(url);
    }
}
