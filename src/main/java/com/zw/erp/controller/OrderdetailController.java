package com.zw.erp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zw.erp.pojo.Emp;
import com.zw.erp.pojo.Orderdetail;
import com.zw.erp.service.OrderdetailService;

@Controller
public class OrderdetailController {
	@Autowired
	private OrderdetailService orderdetailService;
	@Autowired
	private HttpSession httpSession;
	
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
			rtn=ajaxReturn(false, "���ʧ��");
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
			rtn=ajaxReturn(false, "ɾ��ʧ��");
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
			rtn=ajaxReturn(false, "����ʧ��");
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
	
	//���
	@ResponseBody
	@RequestMapping("orderdetail_doInStore")
	public Map<String, Object> doInStore(Orderdetail od){
		Map<String,Object> rtn;
		//��ȡ��ǰ��½�û�
		Emp loginUser = (Emp) httpSession.getAttribute("loginUser");
		if(null == loginUser){
			//�û�û�е�½��session��ʧЧ
			rtn=ajaxReturn(false, "�ף�����û�е�½");
			return rtn;
		}
		try {
			//������ϸ���ҵ��
			orderdetailService.doInStore(od.getUuid(),od.getStoreuuid(),loginUser.getUuid(),loginUser.getName());
			rtn=ajaxReturn(true, "���ɹ�");
		} catch (Exception e) {
			rtn=ajaxReturn(false, "���ʧ��");
			e.printStackTrace();
		}
		return rtn;
	}
	
	//���
	@ResponseBody
	@RequestMapping("orderdetail_doOutStore")
	public Map<String, Object> doOutStore(Orderdetail od){
		Map<String,Object> rtn;
		//��ȡ��ǰ��½�û�
		Emp loginUser = (Emp) httpSession.getAttribute("loginUser");
		if(null == loginUser){
			//�û�û�е�½��session��ʧЧ
			rtn=ajaxReturn(false, "�ף�����û�е�½");
			return rtn;
		}
		try {
			//������ϸ���ҵ��
			orderdetailService.doOutStore(od.getUuid(),od.getStoreuuid(),loginUser.getUuid(),loginUser.getName());
			rtn=ajaxReturn(true, "����ɹ�");
		} catch (Exception e) {
			rtn=ajaxReturn(false, "����ʧ��");
			e.printStackTrace();
		}
		return rtn;
	}

	
	//ajax����
	public Map<String, Object> ajaxReturn(boolean success,String message){
		Map<String, Object> rtn = new HashMap<String, Object>();
		rtn.put("success",success);
		rtn.put("message",message);
		return rtn;
	}
}
