package com.siddhraj.URLShortener.dao;

/*
 * Dao layer for the Url
 * 
 * Author: Siddhraj Sisodiya
 */

import com.siddhraj.URLShortener.models.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public interface UrlDao extends JpaRepository<Url, String> {

	public Url findByShortUrl(String shortUrl);

	@Modifying
	@Transactional
	@Query(value = "UPDATE url SET updated_at = now() WHERE id = ?1", nativeQuery = true)
	public void recordUpdateTime(Long id);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM url WHERE updated_at <= ?1", nativeQuery = true)
	public void deleteExpiredUrls(Date expiryDate);
}
