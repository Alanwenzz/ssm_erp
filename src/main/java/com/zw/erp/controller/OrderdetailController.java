package com.zw.erp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zw.erp.pojo.Orderdetail;
import com.zw.erp.service.OrderdetailService;

public class OrderdetailController {
	@Autowired
	private OrderdetailService orderdetailService;
	
	//ҳ��
	@RequestMapping("orderdetail")
	public String orderdetail() {
		return "orderdetail";
	}
	
	//���
	@ResponseBody
	@RequestMapping("orderdetail_add")
	public Map<String, Object> add(Orderdetail orderdetail){
		Map<String, Object> rtn;
		try {
			orderdetailService.add(orderdetail);
			rtn=ajaxReturn(true, "��ӳɹ�");
		} catch (Exception e) {
			// TODO: handle exception
			rtn=ajaxReturn(true, "���ʧ��");
		}
		return rtn;
	}
	//ɾ��
	@ResponseBody
	@RequestMapping("orderdetail_delete")
	public Map<String, Object> delete(long uuid){
		Map<String, Object> rtn;
		try {
			orderdetailService.delete(uuid);
			rtn=ajaxReturn(true, "ɾ���ɹ�");
		} catch (Exception e) {
			// TODO: handle exception
			rtn=ajaxReturn(true, "ɾ��ʧ��");
		}
		return rtn;
	}
	//�޸�
	@ResponseBody
	@RequestMapping("orderdetail_update")
	public Map<String, Object> update(Orderdetail orderdetail){
		Map<String, Object> rtn;
		try {
			orderdetailService.update(orderdetail);
			rtn=ajaxReturn(true, "���³ɹ�");
		} catch (Exception e) {
			// TODO: handle exception
			rtn=ajaxReturn(true, "����ʧ��");
		}
		return rtn;
	}
	
	//����id��ѯ������Ϣ
	@ResponseBody
	@RequestMapping("orderdetail_get")
	public Orderdetail get(long uuid){
		Orderdetail orderdetail=orderdetailService.get(uuid);
		return orderdetail;
	}
	
	//��ѯ
	@ResponseBody
	@RequestMapping("orderdetail_getList")
	public List<Orderdetail> getList(Orderdetail orderdetail){
		List<Orderdetail> ld=orderdetailService.findByCondition(orderdetail);
		return ld;
	}
	
	//ajax����
	public Map<String, Object> ajaxReturn(boolean success,String message){
		Map<String, Object> rtn = new HashMap<String, Object>();
		rtn.put("success",success);
		rtn.put("message",message);
		return rtn;
	}
}
