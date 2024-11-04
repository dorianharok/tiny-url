package com.example.tinyurl.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "url")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TinyUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = false)
    private String originalUrl;
    @Column(nullable = false, unique = true)
    private String tinyUrlKey;
    @Column(nullable = false)
    private Long redirectCount;

    public TinyUrl(String originalUrl, String tinyUrlKey) {
        this.originalUrl = originalUrl;
        this.tinyUrlKey = tinyUrlKey;
        this.redirectCount = 0L;
    }

    public void incrementRedirectCount() {
        this.redirectCount++;
    }
}
