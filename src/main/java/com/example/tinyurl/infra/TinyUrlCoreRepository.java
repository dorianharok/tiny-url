package com.example.tinyurl.infra;

import com.example.tinyurl.domain.TinyUrl;
import com.example.tinyurl.domain.TinyUrlRepository;
import com.example.tinyurl.domain.exception.UrlKeyNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TinyUrlCoreRepository implements TinyUrlRepository {

    private final TinyUrlJpaRepository tinyUrlJpaRepository;

    @Override
    public TinyUrl save(TinyUrl tinyUrl) {
        return tinyUrlJpaRepository.save(tinyUrl);
    }

    @Override
    public Boolean existsByTinyUrlKey(String urlKey) {
        return tinyUrlJpaRepository.existsByTinyUrlKey(urlKey);
    }

    @Override
    public TinyUrl findByTinyUrlKey(String tinyUrlKey) {
        return tinyUrlJpaRepository.findByTinyUrlKey(tinyUrlKey)
                .orElseThrow(UrlKeyNotFoundException::new);
    }

    @Override
    public void deleteAll() {
        tinyUrlJpaRepository.deleteAllInBatch();
    }

    @Override
    public List<TinyUrl> findAll() {
        return tinyUrlJpaRepository.findAll();
    }
}
