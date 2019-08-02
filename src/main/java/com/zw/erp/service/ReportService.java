package com.zw.erp.service;
import java.util.Date;
import java.util.List;
import java.util.Map;


public interface ReportService {
	public List<Map<String, Object>> orderReport(Date stateDate,Date endDate);
	public List<Map<String, Object>> trendReport(int year);
}
