package com.urlshortner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urlshortner.dtos.UrlRequest;
import com.urlshortner.dtos.UrlResponse;
import com.urlshortner.service.UrlService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/urls")
public class UrlController {
	@Autowired
	private UrlService urlService;
	@PostMapping
	public UrlResponse createShortUrl(@Valid @RequestBody UrlRequest request) {
		return urlService.createShortUrl(request);
	}
	@GetMapping("/{shortCode}/analytics")
	public UrlResponse getAnalytics(@PathVariable String shortCode){
		return urlService.getAnalytics(shortCode);
	}
}
