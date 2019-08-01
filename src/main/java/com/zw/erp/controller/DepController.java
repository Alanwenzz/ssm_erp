package com.zw.erp.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zw.erp.pojo.Dep;
import com.zw.erp.service.DepService;

@Controller
public class DepController {
	@Autowired
	private DepService depService;
	
	//页面
	@RequestMapping("dep")
	public String dep() {
		return "dep";
	}
	
	//添加
	@ResponseBody
	@RequestMapping("dep_add")
	public Map<String, Object> add(Dep dep){
		Map<String, Object> rtn;
		try {
			depService.add(dep);
			rtn=ajaxReturn(true, "添加成功");
		} catch (Exception e) {
			// TODO: handle exception
			rtn=ajaxReturn(false, "添加失败");
		}
		return rtn;
	}
	//删除
	@ResponseBody
	@RequestMapping("dep_delete")
	public Map<String, Object> delete(long uuid){
		Map<String, Object> rtn;
		try {
			depService.delete(uuid);
			rtn=ajaxReturn(true, "删除成功");
		} catch (Exception e) {
			// TODO: handle exception
			rtn=ajaxReturn(false, "删除失败");
		}
		return rtn;
	}
	//修改
	@ResponseBody
	@RequestMapping("dep_update")
	public Map<String, Object> update(Dep dep){
		Map<String, Object> rtn;
		try {
			depService.update(dep);
			rtn=ajaxReturn(true, "更新成功");
		} catch (Exception e) {
			// TODO: handle exception
			rtn=ajaxReturn(false, "更新失败");
		}
		return rtn;
	}
	
	//根据id查询部门信息
	@ResponseBody
	@RequestMapping("dep_get")
	public Dep get(long uuid){
		Dep dep=depService.get(uuid);
		return dep;
	}
	
	//查询
	@ResponseBody
	@RequestMapping("dep_getList")
	public List<Dep> getList(Dep dep){
		List<Dep> ld=depService.findByCondition(dep);
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
