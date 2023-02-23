package com.siddhraj.URLShortener.controllers;

/*
 * Controller for Url
 * 
 * Author: Siddhraj Sisodiya
 */

import com.siddhraj.URLShortener.helpers.Constants;
import com.siddhraj.URLShortener.models.Url;
import com.siddhraj.URLShortener.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UrlController {

	@Autowired
	private UrlService urlService;

	/* Create a new shortURL for given longURL */
	@PostMapping(value = "/url")
	@ResponseBody
	public ResponseEntity<Object> createUrl(@RequestBody Url newUrl) {
		return urlService.createUrl(newUrl);
	}

	/* Get longURL for given shortURL */
	@GetMapping(value = "/url")
	@ResponseBody
	public ResponseEntity<Object> getLongUrl(@RequestParam("shorturl") String shortUrl) {
		String longUrl = urlService.getLongUrl(shortUrl);

		if (longUrl != null) {
			return new ResponseEntity<Object>(longUrl, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(Constants.MSG_URL_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
	}

	/* Remove all expired URLs from the database */
	@DeleteMapping(value = "/url")
	@ResponseBody
	public void removeOldUrls() {
		urlService.removeOldUrls();
	}
}
