package com.moviebooking.notification.service.impl;

import com.moviebooking.notification.message.ShortURLResponse;
import com.moviebooking.notification.service.URLShortenerService;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenerServiceImpl implements URLShortenerService {
    @Override
    public ShortURLResponse shortenURL(String originalURL) {
        return null;
    }

    @Override
    public String generateShortURL(String originalURL) {
        return null;
    }
}
