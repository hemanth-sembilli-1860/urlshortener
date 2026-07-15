package com.urlshortener.Scheduler;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.urlshortner.repository.UrlMappingRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UrlCleanerScheduler {
	@Autowired
	private UrlMappingRepository urlMappingRepository;
	
	@Scheduled(cron = "0 0 0 * * *")
	//@Scheduled(fixedRate = 10000)
	public void deleteUrls() {
		urlMappingRepository.deleteByExpiresAtBefore(LocalDateTime.now());
		log.info("Expired URLs deleted");
	}
}
