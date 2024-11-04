package com.example.tinyurl.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class RandomUrlKeyGeneratorTest {

    RandomUrlKeyGenerator randomUrlKeyGenerator = new RandomUrlKeyGenerator();

    @Test
    void Random_key의_길이는_7글자다() {
        String key = randomUrlKeyGenerator.generate();

        assertThat(key.length()).isEqualTo(7);
    }

    @Test
    void Random_key는_BASE62로_인코딩된다() {
        String key = randomUrlKeyGenerator.generate();

        assertThat(key).matches("[A-Za-z0-9]{7}");
    }

    @Test
    void Random_key는_매번_다른_값이_생성된다() {
        String key1 = randomUrlKeyGenerator.generate();
        String key2 = randomUrlKeyGenerator.generate();

        assertThat(key1).isNotEqualTo(key2);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100})
    void 여러번_생성해도_항상_BASE62_문자만_포함한다(int generationCount) {
        for (int i = 0; i < generationCount; i++) {
            String key = randomUrlKeyGenerator.generate();
            assertThat(key)
                    .matches("[A-Za-z0-9]{7}")
                    .hasSize(7);
        }
    }

    @Test
    void 생성된_key는_빈_문자열이나_null이_아니다() {
        String key = randomUrlKeyGenerator.generate();

        assertThat(key)
                .isNotNull()
                .isNotEmpty()
                .isNotBlank();
    }
}