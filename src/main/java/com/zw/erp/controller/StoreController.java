package com.zw.erp.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zw.erp.pojo.Store;
import com.zw.erp.service.StoreService;

@Controller
public class StoreController {
	@Autowired
	private StoreService storeService;
	
	//页面
	@RequestMapping("store")
	public String store() {
		return "store";
	}
	
	//添加
	@ResponseBody
	@RequestMapping("store_add")
	public Map<String, Object> add(Store store){
		Map<String, Object> rtn;
		try {
			storeService.add(store);
			rtn=ajaxReturn(true, "添加成功");
		} catch (Exception e) {
			// TODO: handle exception
			rtn=ajaxReturn(true, "添加失败");
		}
		return rtn;
	}
	//删除
	@ResponseBody
	@RequestMapping("store_delete")
	public Map<String, Object> delete(long uuid){
		Map<String, Object> rtn;
		try {
			storeService.delete(uuid);
			rtn=ajaxReturn(true, "删除成功");
		} catch (Exception e) {
			// TODO: handle exception
			rtn=ajaxReturn(true, "删除失败");
		}
		return rtn;
	}
	//修改
	@ResponseBody
	@RequestMapping("store_update")
	public Map<String, Object> update(Store store){
		Map<String, Object> rtn;
		try {
			storeService.update(store);
			rtn=ajaxReturn(true, "更新成功");
		} catch (Exception e) {
			// TODO: handle exception
			rtn=ajaxReturn(true, "更新失败");
		}
		return rtn;
	}
	
	//根据id查询部门信息
	@ResponseBody
	@RequestMapping("store_get")
	public Store get(long uuid){
		Store store=storeService.get(uuid);
		return store;
	}
	
	//查询
	@ResponseBody
	@RequestMapping("store_getList")
	public List<Store> getList(Store store){
		List<Store> ld=storeService.findByCondition(store);
		return ld;
	}
	
	//ajax返回
	public Map<String, Object> ajaxReturn(boolean success,String message){
		Map<String, Object> rtn = new HashMap<String, Object>();
		rtn.put("success",success);
		rtn.put("message",message);
		return rtn;
	}
	
}
