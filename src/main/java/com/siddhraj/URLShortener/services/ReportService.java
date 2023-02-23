package com.siddhraj.URLShortener.services;

/*
 * Microservices related to Reports
 * 
 * Author: Siddhraj Sisodiya
 */

import com.siddhraj.URLShortener.dao.ReportDao;
import com.siddhraj.URLShortener.models.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReportService {

	@Autowired
	private ReportDao reportDao;

	/* Service which updates the total-click-count for today */
	@Async
	public void updateClickCount() {
		reportDao.updateTotalClicks();
	}

	/*
	 * Service which generates the report of today. Report includes number of
	 * shortUrl created, longUrl added and unique clicks along with the count of
	 * total clicks
	 */
	public Report generateReport() {

		Report report = reportDao.findTodayReport();

		report.setNumShortUrlCreated(reportDao.countShortUrlCreated().longValue());
		report.setNumLongUrlAdded(reportDao.countLongUrlAdded().longValue());
		report.setNumUniqueClicks(reportDao.countUniqueClicks().longValue());

		report = reportDao.save(report);

		return report;
	}

	/* Service which returns the report generated on a particular date */
	public Report getReportByDate(Date queryDate, String dateString) {
//		System.out.println(queryDate);
		if (queryDate == null || queryDate.after(new Date()))
			return null;
		
		Report report = reportDao.findReportByDate(dateString);
		return report;
	}

}
