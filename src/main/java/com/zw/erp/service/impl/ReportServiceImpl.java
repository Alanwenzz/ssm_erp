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
		//���·ݽ��в�ȱ��©
		//map�����ݣ�{"name":4,"y":906.6}
		List<Map<String, Object>> yearData = reportMapper.getSumMoney(year);
		//���շ��ص�����
		List<Map<String, Object>> rtnData = new ArrayList<Map<String, Object>>();
		//key=�·ݣ�ֵ={"name":4,"y":906.6}
		Map<String,Map<String, Object>> yearDataMap = new HashMap<String,Map<String,Object>>();
		//�Ѵ������д��ڵ��·ݵ����ݴ浽yearDataMap��ȥ,��Ϊ�˲�ȱ��©��ʱ���ж����ݿ����Ƿ�������·ݵ�����
		for(Map<String, Object> month : yearData){
			yearDataMap.put(month.get("name") + "", month);
		}
		//��ȫȱ�ٵ��·�����
		Map<String, Object> monthData = null;
		for(int i = 1; i <= 12; i++){
			monthData = yearDataMap.get(i+"");
			//����·�û������
			if(monthData == null){
				//��������
				monthData = new HashMap<String,Object>();
				monthData.put("name", i + "��");
				monthData.put("y", 0);
			}else{
				monthData.put("name", i + "��");
			}
			rtnData.add(monthData);
		}
		return rtnData;
	}

}
