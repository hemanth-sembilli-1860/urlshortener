package com.urlshortner.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.urlshortner.entity.UrlMapping;

@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMapping,Long>{
      Optional<UrlMapping> findByShortCode(String shortCode);
      boolean existsByShortCode(String shortCode);
      void deleteByExpiresAtBefore(LocalDateTime time);
}
