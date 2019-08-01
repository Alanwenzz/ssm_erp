package com.zw.erp.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zw.erp.pojo.Storedetail;
import com.zw.erp.service.StoredetailService;

@Controller
public class StoredetailController {
	@Autowired
	private StoredetailService storedetailService;
	
	//页面
	@RequestMapping("storedetail")
	public String storedetail() {
		return "storedetail";
	}
	
	//添加
	@ResponseBody
	@RequestMapping("storedetail_add")
	public Map<String, Object> add(Storedetail storedetail){
		Map<String, Object> rtn;
		try {
			storedetailService.add(storedetail);
			rtn=ajaxReturn(true, "添加成功");
		} catch (Exception e) {
			// TODO: handle exception
			rtn=ajaxReturn(false, "添加失败");
		}
		return rtn;
	}
	//删除
	@ResponseBody
	@RequestMapping("storedetail_delete")
	public Map<String, Object> delete(long uuid){
		Map<String, Object> rtn;
		try {
			storedetailService.delete(uuid);
			rtn=ajaxReturn(true, "删除成功");
		} catch (Exception e) {
			// TODO: handle exception
			rtn=ajaxReturn(false, "删除失败");
		}
		return rtn;
	}
	//修改
	@ResponseBody
	@RequestMapping("storedetail_update")
	public Map<String, Object> update(Storedetail storedetail){
		Map<String, Object> rtn;
		try {
			storedetailService.update(storedetail);
			rtn=ajaxReturn(true, "更新成功");
		} catch (Exception e) {
			// TODO: handle exception
			rtn=ajaxReturn(false, "更新失败");
		}
		return rtn;
	}
	
	//根据id查询部门信息
	@ResponseBody
	@RequestMapping("storedetail_get")
	public Storedetail get(long uuid){
		Storedetail storedetail=storedetailService.get(uuid);
		return storedetail;
	}
	
	//查询
	@ResponseBody
	@RequestMapping("storedetail_getList")
	public List<Storedetail> getList(Storedetail storedetail){
		List<Storedetail> ld=storedetailService.findByCondition(storedetail);
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
