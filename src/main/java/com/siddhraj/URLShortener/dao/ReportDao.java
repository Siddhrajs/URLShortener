package com.siddhraj.URLShortener.dao;

/*
 * Dao layer for the Report 
 * 
 * Author: Siddhraj Sisodiya
 */

import com.siddhraj.URLShortener.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

public interface ReportDao extends JpaRepository<Report, Integer> {

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO report VALUES(null, now(), 0, 0, 0, 0)", nativeQuery=true)
	public void addTodayRecord();
	
	@Query(value = "SELECT * FROM report WHERE created_at >= curdate()", nativeQuery = true)
	public Report findTodayReport();
	
	@Query(value = "SELECT count(*) FROM url WHERE created_at >= curdate()", nativeQuery = true)
	public BigInteger countShortUrlCreated();

	@Query(value = "SELECT count(DISTINCT long_url) FROM url WHERE created_at >= curdate()", nativeQuery = true)
	public BigInteger countLongUrlAdded();
	
	@Query(value = "SELECT count(*) FROM url WHERE updated_at >= curdate() AND updated_at <> created_at", nativeQuery = true)
	public BigInteger countUniqueClicks();

	@Modifying
	@Transactional
	@Query(value = "UPDATE report SET num_total_clicks=num_total_clicks+1 WHERE created_at >= curdate()", nativeQuery = true)
	public void updateTotalClicks();

	@Query(value = "SELECT * FROM report WHERE DATE(created_at) = ?1", nativeQuery = true)
	public Report findReportByDate(String queryDate);
}
