package com.example.tinyurl.infra;

import com.example.tinyurl.domain.TinyUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TinyUrlJpaRepository extends JpaRepository<TinyUrl, Long> {
    Boolean existsByTinyUrlKey(String url);

    Optional<TinyUrl> findByTinyUrlKey(String urlKey);
}
