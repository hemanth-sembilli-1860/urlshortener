package com.urlshortner.util;

import java.security.SecureRandom;
import org.springframework.stereotype.Component;


@Component
public class ShortCodeGenerator {
	public static final String BASE62_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public static final int SHORT_CODE_LENGTH = 6;
	private final SecureRandom random = new SecureRandom();
	private ShortCodeGenerator() {
	}
	public String generateShortCode() {
		StringBuilder shortCode = new StringBuilder();
		for (int i = 0;i<SHORT_CODE_LENGTH;i++) {
			int index = random.nextInt(BASE62_CHARS.length());
			char randomCharacter = BASE62_CHARS.charAt(index);
			shortCode.append(randomCharacter);
		}
		return shortCode.toString();
	}
}
