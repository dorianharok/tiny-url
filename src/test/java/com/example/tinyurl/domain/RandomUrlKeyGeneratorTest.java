package com.example.tinyurl.domain;

import com.example.tinyurl.domain.exception.LackOfShortenUrlKeyException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RandomUrlKeyGeneratorTest {

    @Mock
    TinyUrlRepository tinyUrlRepository;

    @InjectMocks
    RandomUrlKeyGenerator randomUrlKeyGenerator;

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

    @Test
    void 중복되지_않는_키를_생성한다() {
        // given
        given(tinyUrlRepository.existsByTinyUrlKey(anyString()))
                .willReturn(false);  // 항상 중복되지 않음

        // when
        String key = randomUrlKeyGenerator.getUniqueTinyUrlKey();

        // then
        assertThat(key)
                .matches("[A-Za-z0-9]{7}")
                .hasSize(7);
        verify(tinyUrlRepository).existsByTinyUrlKey(key);
    }

    @Test
    void 연속으로_5번_중복되면_예외가_발생한다() {
        // given
        given(tinyUrlRepository.existsByTinyUrlKey(anyString()))
                .willReturn(true);  // 항상 중복됨

        // when & then
        assertThatThrownBy(() -> randomUrlKeyGenerator.getUniqueTinyUrlKey())
                .isInstanceOf(LackOfShortenUrlKeyException.class);

        verify(tinyUrlRepository, times(5))
                .existsByTinyUrlKey(anyString());
    }

    @Test
    void 중복_발생_후_재시도하여_성공한다() {
        // given
        given(tinyUrlRepository.existsByTinyUrlKey(anyString()))
                .willReturn(true)   // 첫 번째 시도: 중복
                .willReturn(false); // 두 번째 시도: 성공

        // when
        String key = randomUrlKeyGenerator.getUniqueTinyUrlKey();

        // then
        assertThat(key)
                .matches("[A-Za-z0-9]{7}")
                .hasSize(7);
        verify(tinyUrlRepository, times(2))
                .existsByTinyUrlKey(anyString());
    }
}