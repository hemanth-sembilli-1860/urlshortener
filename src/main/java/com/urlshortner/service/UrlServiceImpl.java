package com.urlshortner.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.urlshortner.dtos.UrlRequest;
import com.urlshortner.dtos.UrlResponse;
import com.urlshortner.entity.UrlMapping;
import com.urlshortner.repository.UrlMappingRepository;
import org.springframework.beans.factory.annotation.Value;
import com.urlshortner.util.ShortCodeGenerator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UrlServiceImpl implements UrlService{
	private final UrlMappingRepository urlMappingRepository;
	private final ShortCodeGenerator shortCodeGenerator;
	
	@Value("${app.base-url}")
	private String baseUrl;

	public UrlServiceImpl(UrlMappingRepository urlMappingRepository,
	                      ShortCodeGenerator shortCodeGenerator) {
	    this.urlMappingRepository = urlMappingRepository;
	    this.shortCodeGenerator = shortCodeGenerator;
	}

	@Override
	public UrlResponse createShortUrl(UrlRequest request) {
		String shortCode;
		if (request.getCustomAlias()!=null) {
			if (urlMappingRepository.existsByShortCode(request.getCustomAlias())) {
				log.warn("Alias {} already exists",request.getCustomAlias());
				throw new IllegalArgumentException("Alias Already Exists");
			}
			shortCode = request.getCustomAlias();
		}
		else {
		do {
			shortCode = shortCodeGenerator.generateShortCode();
		}
		while (urlMappingRepository.existsByShortCode(shortCode));
		}
		LocalDateTime createdAt = LocalDateTime.now();
		LocalDateTime expiresAt;
		if (request.getExpiryDays() == null) {
			expiresAt = createdAt.plusDays(30);
		}
		else {
			expiresAt = createdAt.plusDays(request.getExpiryDays());
		}
		UrlMapping urlMapping  = UrlMapping.builder()
				.originalUrl(request.getOriginalUrl())
				.shortCode(shortCode)
				.createdAt(createdAt)
				.expiresAt(expiresAt)
				.clickCount(0L)
				.build();
		log.info("Creating short URL for {}", request.getOriginalUrl());
		UrlMapping savedUrl = urlMappingRepository.save(urlMapping);
		UrlResponse response = new UrlResponse(savedUrl.getOriginalUrl(),
				savedUrl.getShortCode(),baseUrl + "/" + savedUrl.getShortCode(),
                savedUrl.getCreatedAt(),savedUrl.getExpiresAt(),savedUrl.getClickCount());
		return response;
	}
	@Cacheable(value = "urls",key = "#shortCode")
	@Override
	public String redirectToOriginalUrl(String shortCode) {
		
		log.debug("Fetching URL from database for {}", shortCode);
		 log.info("Redirect requested for {}",shortCode);
		 
		Optional<UrlMapping> optionalUrl = urlMappingRepository.findByShortCode(shortCode);
		if (optionalUrl.isEmpty()) {
			throw new IllegalArgumentException("Short Url not Found");
		}
		UrlMapping urlMapping  = optionalUrl.get();
		if (urlMapping.getExpiresAt().isBefore(LocalDateTime.now())) {
			log.warn("Short URL {} has expired", shortCode);
			throw new IllegalArgumentException("Short Url has Expired");
		}
		urlMapping.setClickCount(urlMapping.getClickCount()+1);
		urlMappingRepository.save(urlMapping);
		return urlMapping.getOriginalUrl();
	}
	@Override 
	public UrlResponse getAnalytics(String shortCode) {
		Optional<UrlMapping> optionalUrl = urlMappingRepository.findByShortCode(shortCode);
		if (optionalUrl.isEmpty()) {
			log.error("Short URL {} not found",shortCode);
			throw new IllegalArgumentException("Short Url not Found");
		}
		UrlMapping urlMapping  = optionalUrl.get();
		UrlResponse response = new UrlResponse(
			    urlMapping.getOriginalUrl(),
			    urlMapping.getShortCode(),
			    baseUrl + "/" + urlMapping.getShortCode(),
			    urlMapping.getCreatedAt(),
			    urlMapping.getExpiresAt(),
			    urlMapping.getClickCount()
			);
		return response;
	}
}
