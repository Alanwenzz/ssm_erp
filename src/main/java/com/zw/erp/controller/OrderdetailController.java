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
	
	//页面
	@RequestMapping("orderdetail")
	public String orderdetail() {
		return "orderdetail";
	}
	
	//添加
	@ResponseBody
	@RequestMapping("orderdetail_add")
	public Map<String, Object> add(Orderdetail orderdetail){
		Map<String, Object> rtn;
		try {
			orderdetailService.add(orderdetail);
			rtn=ajaxReturn(true, "添加成功");
		} catch (Exception e) {
			// TODO: handle exception
			rtn=ajaxReturn(false, "添加失败");
		}
		return rtn;
	}
	//删除
	@ResponseBody
	@RequestMapping("orderdetail_delete")
	public Map<String, Object> delete(long uuid){
		Map<String, Object> rtn;
		try {
			orderdetailService.delete(uuid);
			rtn=ajaxReturn(true, "删除成功");
		} catch (Exception e) {
			// TODO: handle exception
			rtn=ajaxReturn(false, "删除失败");
		}
		return rtn;
	}
	//修改
	@ResponseBody
	@RequestMapping("orderdetail_update")
	public Map<String, Object> update(Orderdetail orderdetail){
		Map<String, Object> rtn;
		try {
			orderdetailService.update(orderdetail);
			rtn=ajaxReturn(true, "更新成功");
		} catch (Exception e) {
			// TODO: handle exception
			rtn=ajaxReturn(false, "更新失败");
		}
		return rtn;
	}
	
	//根据id查询部门信息
	@ResponseBody
	@RequestMapping("orderdetail_get")
	public Orderdetail get(long uuid){
		Orderdetail orderdetail=orderdetailService.get(uuid);
		return orderdetail;
	}
	
	//查询
	@ResponseBody
	@RequestMapping("orderdetail_getList")
	public List<Orderdetail> getList(Orderdetail orderdetail){
		List<Orderdetail> ld=orderdetailService.findByCondition(orderdetail);
		return ld;
	}
	
	//入库
	@ResponseBody
	@RequestMapping("orderdetail_doInStore")
	public Map<String, Object> doInStore(Orderdetail od){
		Map<String,Object> rtn;
		//获取当前登陆用户
		Emp loginUser = (Emp) httpSession.getAttribute("loginUser");
		if(null == loginUser){
			//用户没有登陆，session已失效
			rtn=ajaxReturn(false, "亲！您还没有登陆");
			return rtn;
		}
		try {
			//调用明细入库业务
			orderdetailService.doInStore(od.getUuid(),od.getStoreuuid(),loginUser.getUuid(),loginUser.getName());
			rtn=ajaxReturn(true, "入库成功");
		} catch (Exception e) {
			rtn=ajaxReturn(false, "入库失败");
			e.printStackTrace();
		}
		return rtn;
	}
	
	//入库
	@ResponseBody
	@RequestMapping("orderdetail_doOutStore")
	public Map<String, Object> doOutStore(Orderdetail od){
		Map<String,Object> rtn;
		//获取当前登陆用户
		Emp loginUser = (Emp) httpSession.getAttribute("loginUser");
		if(null == loginUser){
			//用户没有登陆，session已失效
			rtn=ajaxReturn(false, "亲！您还没有登陆");
			return rtn;
		}
		try {
			//调用明细入库业务
			orderdetailService.doOutStore(od.getUuid(),od.getStoreuuid(),loginUser.getUuid(),loginUser.getName());
			rtn=ajaxReturn(true, "出库成功");
		} catch (Exception e) {
			rtn=ajaxReturn(false, "出库失败");
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
