package com.siddhraj.URLShortener.services;

/* 
 * Cached service for faster retrieval of longURLs for a given shortURL
 * 
 * Author: Siddhraj Sisodiya
 */

import com.siddhraj.URLShortener.dao.UrlDao;
import com.siddhraj.URLShortener.models.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheUrlService {

	@Autowired
	private UrlDao urlDao;

//	@Cacheable("shortToLong")
	public String findLongUrl(String shortUrl) {
		Url url = urlDao.findByShortUrl(shortUrl);
		if (url != null) {
			urlDao.recordUpdateTime(url.getId());
		}

		return url.getLongUrl();
	}
}
