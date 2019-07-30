package com.zw.erp.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zw.erp.pojo.Supplier;
import com.zw.erp.service.SupplierService;

@Controller
public class SupplierController {
	@Autowired
	private SupplierService supplierService;
	
	//页面
	@RequestMapping("supplier")
	public String supplier() {
		return "supplier";
	}
	
	//添加
	@ResponseBody
	@RequestMapping("supplier_add")
	public Map<String, Object> add(Supplier supplier,String type){
		Map<String, Object> rtn;
		try {
			supplier.setType(type);
			supplierService.add(supplier);
			rtn=ajaxReturn(true, "添加成功");
		} catch (Exception e) {
			// TODO: handle exception
			rtn=ajaxReturn(false, "添加失败");
		}
		return rtn;
	}
	//删除
	@ResponseBody
	@RequestMapping("supplier_delete")
	public Map<String, Object> delete(long uuid){
		Map<String, Object> rtn;
		try {
			supplierService.delete(uuid);
			rtn=ajaxReturn(true, "删除成功");
		} catch (Exception e) {
			// TODO: handle exception
			rtn=ajaxReturn(false, "删除失败");
		}
		return rtn;
	}
	//修改
	@ResponseBody
	@RequestMapping("supplier_update")
	public Map<String, Object> update(Supplier supplier,String type,long uuid){
		Map<String, Object> rtn;
		try {
			supplier.setUuid(uuid);
			supplier.setType(type);
			supplierService.update(supplier);
			rtn=ajaxReturn(true, "更新成功");
		} catch (Exception e) {
			// TODO: handle exception
			rtn=ajaxReturn(false, "更新失败");
		}
		return rtn;
	}
	
	//根据type查询
	@ResponseBody
	@RequestMapping("supplier_getList")
	public List<Supplier> getByType(String type){
		List<Supplier> supplier=supplierService.getByType(type);
		return supplier;
	}
	
	//根据type显示页面
	@ResponseBody
	@RequestMapping("supplier_get")
	public Supplier get(long uuid){
		Supplier ld=supplierService.get(uuid);
		return ld;
	}
		
//	//查询
//	@ResponseBody
//	@RequestMapping("supplier_getList")
//	public List<Supplier> getList(Supplier supplier){
//		List<Supplier> ld=supplierService.findByCondition(supplier);
//		return ld;
//	}
	
	//ajax返回
	public Map<String, Object> ajaxReturn(boolean success,String message){
		Map<String, Object> rtn = new HashMap<String, Object>();
		rtn.put("success",success);
		rtn.put("message",message);
		return rtn;
	}
	
}
