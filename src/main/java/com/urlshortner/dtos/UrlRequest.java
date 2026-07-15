package com.urlshortner.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UrlRequest {
	@NotBlank(message = "URL cannot be empty")
	@Pattern(regexp = "^(http|https)://.+$",message = "URL must start with http:// or https://")
	private String originalUrl;
	@Pattern(regexp = "^[A-Za-z0-9_-]{3,20}$",message = "Alias must contain only letters, numbers ,_ or - and be 3-20 characters long")
	private String customAlias;
	@Min(value = 1,message = "Minimum expiry is 1 Day")
	@Max(value = 365,message = "Maximum expiry is 365 Days")
	private Integer expiryDays;

}
