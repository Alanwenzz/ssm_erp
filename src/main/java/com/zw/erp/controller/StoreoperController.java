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
	@RequestMapping("storeoper_listByPage")
	public Map<String, Object> listByPage(Storeoper storeoper,@DateTimeFormat(pattern="yyyy-MM-dd")Date opertime2,int page,int rows){
		//第一条数据
		int firstResult = (page -1) * rows;
		//总条数
		long total =storeoperService.getCount(storeoper,opertime2);
		//查询的数据
		List<Storeoper> list=storeoperService.getListByPage(storeoper,opertime2,firstResult, rows);
		//{total: total, rows:[]}
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", total);
		mapData.put("rows", list);
		return mapData;
	}
	
	//ajax返回
	public Map<String, Object> ajaxReturn(boolean success,String message){
		Map<String, Object> rtn = new HashMap<String, Object>();
		rtn.put("success",success);
		rtn.put("message",message);
		return rtn;
	}
	
}
