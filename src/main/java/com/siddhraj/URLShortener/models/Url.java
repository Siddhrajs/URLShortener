package com.siddhraj.URLShortener.models;

/*
 * Url Schema
 * 
 * Author: Siddhraj Sisodiya
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Url {

	/* Fields */
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "short_url", length = 22, unique = true, nullable = false)
	private String shortUrl;

	@Column(name = "long_url", length = 2084)
	private String longUrl;

	@JsonIgnore
	@Column(name = "created_at")
	private Date createdAt;

	@JsonIgnore
	@Column(name = "updated_at")
	private Date updatedAt;

	/* Operation based time assignment */
	@PrePersist
	public void beforeCreation() {
		createdAt = new Date();
		updatedAt = createdAt;
	}

	@PreUpdate
	public void beforeUpdation() {
		updatedAt = new Date();
	}

	/* Getter and Setter Methods */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
