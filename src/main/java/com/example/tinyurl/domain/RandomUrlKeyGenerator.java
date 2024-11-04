package com.example.tinyurl.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
@RequiredArgsConstructor
public class RandomUrlKeyGenerator implements UrlKeyGenerator {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int BASE = ALPHABET.length();
    private static final int URL_LENGTH = 7;

    private final TinyUrlRepository tinyUrlRepository;

    @Override
    public String generate() {
        StringBuilder sb = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < URL_LENGTH; i++) {
            int index = random.nextInt(BASE);
            sb.append(ALPHABET.charAt(index));
        }

        return sb.toString();
    }

    @Override
    public String getUniqueTinyUrlKey() {
        final int MAX_RETRY_COUNT = 5;
        int count = 0;

        while(count++ < MAX_RETRY_COUNT) {
            String shortenUrlKey = generate();
            if(!tinyUrlRepository.existsByTinyUrlKey(shortenUrlKey)) {
                return shortenUrlKey;
            }
        }

        throw new LackOfShortenUrlKeyException();
    }
}
