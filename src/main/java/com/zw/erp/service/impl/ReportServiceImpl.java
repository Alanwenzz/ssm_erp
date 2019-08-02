package com.zw.erp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zw.erp.mapper.ReportMapper;
import com.zw.erp.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService{
	@Autowired
	private ReportMapper reportMapper;

	public List<Map<String, Object>> orderReport(Date stateDate,Date endDate) {
		// TODO Auto-generated method stub
		return reportMapper.orderReport(stateDate,endDate);
	}

	public List<Map<String, Object>> trendReport(int year) {
		// TODO Auto-generated method stub
		//对月份进行查缺补漏
		//map的内容：{"name":4,"y":906.6}
		List<Map<String, Object>> yearData = reportMapper.getSumMoney(year);
		//最终返回的数据
		List<Map<String, Object>> rtnData = new ArrayList<Map<String, Object>>();
		//key=月份，值={"name":4,"y":906.6}
		Map<String,Map<String, Object>> yearDataMap = new HashMap<String,Map<String,Object>>();
		//把从数据中存在的月份的数据存到yearDataMap中去,是为了查缺补漏的时候，判断数据库中是否有这个月份的数据
		for(Map<String, Object> month : yearData){
			yearDataMap.put(month.get("name") + "", month);
		}
		//补全缺少的月份数据
		Map<String, Object> monthData = null;
		for(int i = 1; i <= 12; i++){
			monthData = yearDataMap.get(i+"");
			//这个月份没有数据
			if(monthData == null){
				//补回数据
				monthData = new HashMap<String,Object>();
				monthData.put("name", i + "月");
				monthData.put("y", 0);
			}else{
				monthData.put("name", i + "月");
			}
			rtnData.add(monthData);
		}
		return rtnData;
	}

}
