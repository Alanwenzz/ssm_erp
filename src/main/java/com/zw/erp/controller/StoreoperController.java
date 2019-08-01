package com.zw.erp.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zw.erp.pojo.Storeoper;
import com.zw.erp.service.StoreoperService;

@Controller
public class StoreoperController {
	@Autowired
	private StoreoperService storeoperService;
	
	//页面
	@RequestMapping("storeoper")
	public String storeoper() {
		return "storeoper";
	}
	
	//添加
	@ResponseBody
	@RequestMapping("storeoper_add")
	public Map<String, Object> add(Storeoper storeoper){
		Map<String, Object> rtn;
		try {
			storeoperService.add(storeoper);
			rtn=ajaxReturn(true, "添加成功");
		} catch (Exception e) {
			// TODO: handle exception
			rtn=ajaxReturn(false, "添加失败");
		}
		return rtn;
	}
	//删除
	@ResponseBody
	@RequestMapping("storeoper_delete")
	public Map<String, Object> delete(long uuid){
		Map<String, Object> rtn;
		try {
			storeoperService.delete(uuid);
			rtn=ajaxReturn(true, "删除成功");
		} catch (Exception e) {
			// TODO: handle exception
			rtn=ajaxReturn(false, "删除失败");
		}
		return rtn;
	}
	//修改
	@ResponseBody
	@RequestMapping("storeoper_update")
	public Map<String, Object> update(Storeoper storeoper){
		Map<String, Object> rtn;
		try {
			storeoperService.update(storeoper);
			rtn=ajaxReturn(true, "更新成功");
		} catch (Exception e) {
			// TODO: handle exception
			rtn=ajaxReturn(false, "更新失败");
		}
		return rtn;
	}
	
	//根据id查询部门信息
	@ResponseBody
	@RequestMapping("storeoper_get")
	public Storeoper get(long uuid){
		Storeoper storeoper=storeoperService.get(uuid);
		return storeoper;
	}
	
	//查询
	@ResponseBody
	@RequestMapping("storeoper_getList")
	public List<Storeoper> getList(Storeoper storeoper){
		List<Storeoper> ld=storeoperService.findByCondition(storeoper);
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
