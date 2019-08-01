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
	
	//订单添加
	@RequestMapping("orders_add")
	@ResponseBody
	public Map<String, Object> orders_add(String type,long supplieruuid,String json) {
		Map<String, Object> rtn;
		Emp loginUser = (Emp)httpSession.getAttribute("loginUser");
		if(null == loginUser){
			//用户没有登陆，session已失效
			rtn=ajaxReturn(false, "亲！您还没有登陆");
			return rtn;
		}
		try {
			Orders orders=new Orders();
			//订单创建者编号
			orders.setCreater(loginUser.getUuid());
			//订单创建者名字
			orders.setCreaterName(loginUser.getName());
			//设置供应商编号
			orders.setSupplieruuid(supplieruuid);
			//设置类型
			orders.setType(type);
			//设置供应商名称
			orders.setSupplierName(supplierService.get(supplieruuid).getName());
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
	public Map<String, Object> getList(String state,String type,int page,int rows) {
		//第一条数据
		int firstResult = (page -1) * rows;
		//总条数
		long total = ordersService.getCount();
		//查询的数据
		List<Orders> list=ordersService.getList(state,type,firstResult, rows);
		//{total: total, rows:[]}
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", total);
		mapData.put("rows", list);
		return mapData;
	}
	
	//订单审核
	@RequestMapping("orders_doCheck")
	@ResponseBody
	public Map<String,Object> doCheck(long uuid){
		Map<String,Object> rtn;
		//获取当前登陆用户
		Emp loginUser = (Emp) httpSession.getAttribute("loginUser");
		if(null == loginUser){
			//用户没有登陆，session已失效
			rtn=ajaxReturn(false, "亲！您还没有登陆");
			return rtn;
		}
		try {
			//调用审核业务
			ordersService.doCheck(uuid, loginUser.getUuid(),loginUser.getName());
			rtn=ajaxReturn(true, "审核成功");
		} catch (Exception e) {
			rtn=ajaxReturn(false, "审核失败");
			e.printStackTrace();
		}
		return rtn;
	}
	
	//订单确认
	@RequestMapping("orders_doStart")
	@ResponseBody
	public Map<String,Object> doStart(long uuid){
		Map<String,Object> rtn;
		//获取当前登陆用户
		Emp loginUser = (Emp) httpSession.getAttribute("loginUser");
		if(null == loginUser){
			//用户没有登陆，session已失效
			rtn=ajaxReturn(false, "亲！您还没有登陆");
			return rtn;
		}
		try {
			//调用审核业务
			ordersService.doStart(uuid, loginUser.getUuid(),loginUser.getName());
			rtn=ajaxReturn(true, "确认成功");
		} catch (Exception e) {
			rtn=ajaxReturn(false, "确认失败");
			e.printStackTrace();
		}
		return rtn;
	}
	
	//ajax返回
	public Map<String, Object> ajaxReturn(boolean success,String message){
		Map<String, Object> rtn = new HashMap<String, Object>();
		rtn.put("success",success);
		rtn.put("message",message);
		return rtn;
	}
}
