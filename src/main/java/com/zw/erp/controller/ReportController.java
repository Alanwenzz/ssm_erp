package com.zw.erp.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zw.erp.service.ReportService;

@Controller
public class ReportController {
	@Autowired
	private ReportService reportService;
	
	@RequestMapping("reportOrder")
	public String reportOrder() {
		return "report_order";
	}
	
	@RequestMapping("reportTrend")
	public String reportTrend() {
		return "report_trend";
	}
	
	@ResponseBody
	@RequestMapping("report_orderReport")
	public List<Map<String , Object>> orderReport(@DateTimeFormat(pattern="yyyy-MM-dd")Date stateDate,@DateTimeFormat(pattern="yyyy-MM-dd")Date endDate){
		return reportService.orderReport(stateDate,endDate);
	}
	
	@ResponseBody
	@RequestMapping("report_trendReport")
	public List<Map<String , Object>> trendReport(int year){
		return reportService.trendReport(year);
	}
}
