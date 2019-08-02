package com.zw.erp.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ReportMapper {
	public List<Map<String, Object>> orderReport(@Param("stateDate")Date stateDate,@Param("endDate")Date endDate);
	public List<Map<String, Object>> getSumMoney(@Param("cyear")int cyear);
}
