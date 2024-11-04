package com.example.tinyurl.domain;

public interface UrlKeyGenerator {
    String generate();

    String getUniqueTinyUrlKey();
}
