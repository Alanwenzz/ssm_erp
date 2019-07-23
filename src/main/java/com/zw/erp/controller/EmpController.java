package com.zw.erp.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zw.erp.pojo.Emp;
import com.zw.erp.service.EmpService;

@Controller
public class EmpController {
	@Autowired
	private EmpService empService;
	
	//页面
	@RequestMapping("emp2")
	public String emp2() {
		return "emp2";
	}
	
	//页面
	@RequestMapping("emp")
	public String emp() {
		return "emp";
	}
	
	//添加
	@ResponseBody
	@RequestMapping("emp_add")
	public Map<String, Object> add(Emp emp){
		Map<String, Object> rtn;
		try {
			empService.add(emp);
			rtn=ajaxReturn(true, "添加成功");
		} catch (Exception e) {
			// TODO: handle exception
			rtn=ajaxReturn(true, "添加失败");
		}
		return rtn;
	}
	//删除
	@ResponseBody
	@RequestMapping("emp_delete")
	public Map<String, Object> delete(long uuid){
		Map<String, Object> rtn;
		try {
			empService.delete(uuid);
			rtn=ajaxReturn(true, "删除成功");
		} catch (Exception e) {
			// TODO: handle exception
			rtn=ajaxReturn(true, "删除失败");
		}
		return rtn;
	}
	//修改
	@ResponseBody
	@RequestMapping("emp_update")
	public Map<String, Object> update(Emp emp){
		Map<String, Object> rtn;
		try {
			empService.update(emp);
			rtn=ajaxReturn(true, "更新成功");
		} catch (Exception e) {
			// TODO: handle exception
			rtn=ajaxReturn(true, "更新失败");
		}
		return rtn;
	}
	//根据id查询用户信息
	@ResponseBody
	@RequestMapping("emp_get")
	public Emp get(long uuid){
		Emp emp=empService.get(uuid);
		return emp;
	}
	//查询
	@ResponseBody
	@RequestMapping("emp_getList")
	public List<Emp> getList(Emp emp,@DateTimeFormat(pattern ="yyyy-MM-dd")Date birthday2){		
		List<Emp> lEmps=empService.findByCondition(emp, birthday2);
		return lEmps;
	}
	
	//ajax返回
	public Map<String, Object> ajaxReturn(boolean success,String message){
		Map<String, Object> rtn = new HashMap<String, Object>();
		rtn.put("success",success);
		rtn.put("message",message);
		return rtn;
	}
	
}
