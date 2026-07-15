package com.urlshortner.service;

import com.urlshortner.dtos.UrlRequest;
import com.urlshortner.dtos.UrlResponse;

public interface UrlService {
	UrlResponse createShortUrl(UrlRequest request);
	String redirectToOriginalUrl(String shortCode);
	UrlResponse getAnalytics(String shortCode);
}
