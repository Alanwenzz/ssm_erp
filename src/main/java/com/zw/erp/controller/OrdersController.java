package com.zw.erp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.zw.erp.pojo.Emp;
import com.zw.erp.pojo.Orderdetail;
import com.zw.erp.pojo.Orders;
import com.zw.erp.service.OrdersService;
import com.zw.erp.service.SupplierService;



@Controller
public class OrdersController {
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private SupplierService supplierService;
	@Autowired
	private HttpSession httpSession;
	
	@RequestMapping("orders")
	public String orders() {
		return "orders";
	}
	
	//�������
	@RequestMapping("orders_add")
	@ResponseBody
	public Map<String, Object> orders_add(String type,long supplieruuid,String json) {
		Map<String, Object> rtn;
		Emp loginUser = (Emp)httpSession.getAttribute("loginUser");
		if(null == loginUser){
			//�û�û�е�½��session��ʧЧ
			rtn=ajaxReturn(false, "�ף�����û�е�½");
			return rtn;
		}
		try {
			Orders orders=new Orders();
			//���������߱��
			orders.setCreater(loginUser.getUuid());
			//��������������
			orders.setCreaterName(loginUser.getName());
			//���ù�Ӧ�̱��
			orders.setSupplieruuid(supplieruuid);
			//��������
			orders.setType(type);
			//���ù�Ӧ������
			orders.setSupplierName(supplierService.get(supplieruuid).getName());
			//JSON.parseArray  ��json�ַ���ת����
			List<Orderdetail> detailList = JSON.parseArray(json,Orderdetail.class);
			//������ϸ
			orders.setOrderDetails(detailList);
			//System.out.println(detailList.size());
			ordersService.add(orders);
			rtn=ajaxReturn(true, "��Ӷ����ɹ�");
		} catch (Exception e) {
			rtn=ajaxReturn(false, "��Ӷ���ʧ��");
			e.printStackTrace();
		}
		return rtn;
	}
	
	@RequestMapping("orders_getList")
	@ResponseBody
	public Map<String, Object> getList(String state,String type,int page,int rows) {
		//��һ������
		int firstResult = (page -1) * rows;
		//������
		long total = ordersService.getCount();
		//��ѯ������
		List<Orders> list=ordersService.getList(state,type,firstResult, rows);
		//{total: total, rows:[]}
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", total);
		mapData.put("rows", list);
		return mapData;
	}
	
	//�������
	@RequestMapping("orders_doCheck")
	@ResponseBody
	public Map<String,Object> doCheck(long uuid){
		Map<String,Object> rtn;
		//��ȡ��ǰ��½�û�
		Emp loginUser = (Emp) httpSession.getAttribute("loginUser");
		if(null == loginUser){
			//�û�û�е�½��session��ʧЧ
			rtn=ajaxReturn(false, "�ף�����û�е�½");
			return rtn;
		}
		try {
			//�������ҵ��
			ordersService.doCheck(uuid, loginUser.getUuid(),loginUser.getName());
			rtn=ajaxReturn(true, "��˳ɹ�");
		} catch (Exception e) {
			rtn=ajaxReturn(false, "���ʧ��");
			e.printStackTrace();
		}
		return rtn;
	}
	
	//����ȷ��
	@RequestMapping("orders_doStart")
	@ResponseBody
	public Map<String,Object> doStart(long uuid){
		Map<String,Object> rtn;
		//��ȡ��ǰ��½�û�
		Emp loginUser = (Emp) httpSession.getAttribute("loginUser");
		if(null == loginUser){
			//�û�û�е�½��session��ʧЧ
			rtn=ajaxReturn(false, "�ף�����û�е�½");
			return rtn;
		}
		try {
			//�������ҵ��
			ordersService.doStart(uuid, loginUser.getUuid(),loginUser.getName());
			rtn=ajaxReturn(true, "ȷ�ϳɹ�");
		} catch (Exception e) {
			rtn=ajaxReturn(false, "ȷ��ʧ��");
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
