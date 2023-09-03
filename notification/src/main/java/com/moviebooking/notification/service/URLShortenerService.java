package com.moviebooking.notification.service;

import com.moviebooking.notification.message.ShortURLResponse;

public interface URLShortenerService {
    ShortURLResponse shortenURL(String originalURL);

    String generateShortURL(String originalURL);
}