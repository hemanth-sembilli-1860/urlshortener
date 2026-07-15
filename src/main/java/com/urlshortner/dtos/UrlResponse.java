package com.urlshortner.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlResponse {
	private String originalUrl;
	private String shortCode;
	private String shortUrl;
	private LocalDateTime createdAt;
	private LocalDateTime expiresAt;
	private Long clickCount;

	

}
