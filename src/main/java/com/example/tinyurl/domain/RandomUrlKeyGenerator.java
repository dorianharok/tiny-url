package com.example.tinyurl.domain;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class RandomUrlKeyGenerator implements UrlKeyGenerator {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int BASE = ALPHABET.length();
    private static final int URL_LENGTH = 7;

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
}
