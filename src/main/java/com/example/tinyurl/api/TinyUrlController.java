package com.example.tinyurl.api;

import com.example.tinyurl.api.request.CreateTinyUrlRequest;
import com.example.tinyurl.api.response.CreateTinyUrlResponse;
import com.example.tinyurl.domain.TinyUrlInfo;
import com.example.tinyurl.domain.TinyUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
public class TinyUrlController {

    private final TinyUrlService tinyUrlService;

    @PostMapping("/tiny-url")
    public ResponseEntity<CreateTinyUrlResponse> createTinyUrl(@RequestBody CreateTinyUrlRequest request) {
        TinyUrlInfo info = tinyUrlService.createTinyUrl(request.originalUrl());

        return ResponseEntity.ok(new CreateTinyUrlResponse(info));
    }

    @GetMapping("/{tinyUrlKey}")
    public ResponseEntity<Void> redirectTinyUrl(@PathVariable String tinyUrlKey) throws URISyntaxException {
        TinyUrlInfo info = tinyUrlService.getOriginalUrlByTinyUrl(tinyUrlKey);
        URI redirectUri = new URI(info.originalUrl());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(redirectUri);
        headers.setCacheControl(CacheControl.maxAge(90, TimeUnit.SECONDS));

        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }
}
