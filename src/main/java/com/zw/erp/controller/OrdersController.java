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



@Controller
public class OrdersController {
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private HttpSession httpSession;
	
	@RequestMapping("orders")
	public String orders() {
		return "orders";
	}
	
	@RequestMapping("orders_add")
	@ResponseBody
	public Map<String, Object> orders_add(String type,long supplieruuid,String json) {
		System.out.println(json);
		Map<String, Object> rtn;
		Emp loginUser = (Emp)httpSession.getAttribute("loginUser");
		if(null == loginUser){
			//�û�û�е�½��session��ʧЧ
			rtn=ajaxReturn(false, "�ף�����û�е�½");
			return rtn;
		}
		try {
			Orders orders=new Orders();
			//����������
			orders.setCreater(loginUser.getUuid());
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
	public List<Orders> getList(String type) {
		return ordersService.getList(type);
	}
	
	//ajax����
	public Map<String, Object> ajaxReturn(boolean success,String message){
		Map<String, Object> rtn = new HashMap<String, Object>();
		rtn.put("success",success);
		rtn.put("message",message);
		return rtn;
	}
}
