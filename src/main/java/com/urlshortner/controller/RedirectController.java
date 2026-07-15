package com.urlshortner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.urlshortner.service.UrlService;

@RestController
@RequestMapping
public class RedirectController {
	@Autowired
	private UrlService urlService;
	@GetMapping("/{shortCode}")
	public RedirectView redirectToShortUrl(@PathVariable String shortCode) {
		String originalUrl = urlService.redirectToOriginalUrl(shortCode);
		return new RedirectView(originalUrl);
	}
}
