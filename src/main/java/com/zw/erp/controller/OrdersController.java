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
			//用户没有登陆，session已失效
			rtn=ajaxReturn(false, "亲！您还没有登陆");
			return rtn;
		}
		try {
			Orders orders=new Orders();
			//订单创建者
			orders.setCreater(loginUser.getUuid());
			//JSON.parseArray  把json字符串转数组
			List<Orderdetail> detailList = JSON.parseArray(json,Orderdetail.class);
			//订单明细
			orders.setOrderDetails(detailList);
			//System.out.println(detailList.size());
			ordersService.add(orders);
			rtn=ajaxReturn(true, "添加订单成功");
		} catch (Exception e) {
			rtn=ajaxReturn(false, "添加订单失败");
			e.printStackTrace();
		}
		return rtn;
	}
	
	@RequestMapping("orders_getList")
	@ResponseBody
	public List<Orders> getList(String type) {
		return ordersService.getList(type);
	}
	
	//ajax返回
	public Map<String, Object> ajaxReturn(boolean success,String message){
		Map<String, Object> rtn = new HashMap<String, Object>();
		rtn.put("success",success);
		rtn.put("message",message);
		return rtn;
	}
}
