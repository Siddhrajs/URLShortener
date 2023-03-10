package com.siddhraj.URLShortener.controllers;

/*
 * Controller for Report
 * 
 * Author: Siddhraj Sisodiya
 */

import com.siddhraj.URLShortener.helpers.Constants;
import com.siddhraj.URLShortener.models.Report;
import com.siddhraj.URLShortener.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ReportController {

	@Autowired
	private ReportService reportService;

	/* Generate today's report */
	@RequestMapping(value = "/report", method = RequestMethod.POST)
	@ResponseBody
	public Report generateTodayReport() {
		return reportService.generateReport();
	}
	
	/* Get report by date in dd-mm-yyyy format */
	@RequestMapping(value = "/report", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getReportByDate(@RequestParam("date") String queryDateString) {
		
		Date queryDate = null;
		try {
			queryDate = new SimpleDateFormat("dd-mm-yyyy").parse(queryDateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String[] tempStrings = queryDateString.split("-");
		queryDateString = tempStrings[2] + "-" + tempStrings[1] + "-" + tempStrings[0];
		
		Report report = reportService.getReportByDate(queryDate,queryDateString);
		if (report != null) {
			return new ResponseEntity<Object>(report, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(Constants.MSG_REPORT_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
	}
}
