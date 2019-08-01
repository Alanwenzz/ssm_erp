package com.zw.erp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zw.erp.pojo.Goodstype;
import com.zw.erp.service.GoodstypeService;

@Controller
public class GoodstypeController {
	@Autowired
	public GoodstypeService goodstypeService;
	
	@RequestMapping("goodstype")
	public String goodstypetype_main() {
		return "goodstype";
	}
	
	//添加
	@ResponseBody
	@RequestMapping("goodstype_add")
	public Map<String, Object> add(Goodstype goodstype){
		Map<String, Object> rtn;
		try {
			goodstypeService.add(goodstype);
			rtn=ajaxReturn(true, "添加成功");
		} catch (Exception e) {
			// TODO: handle exception
			rtn=ajaxReturn(false, "添加失败");
		}
		return rtn;
	}
	//删除
	@ResponseBody
	@RequestMapping("goodstype_delete")
	public Map<String, Object> delete(long uuid){
		Map<String, Object> rtn;
		try {
			goodstypeService.delete(uuid);
			rtn=ajaxReturn(true, "删除成功");
		} catch (Exception e) {
			// TODO: handle exception
			rtn=ajaxReturn(false, "删除失败");
		}
		return rtn;
	}
	//修改
	@ResponseBody
	@RequestMapping("goodstype_update")
	public Map<String, Object> update(Goodstype goodstype){
		Map<String, Object> rtn;
		try {
			goodstypeService.update(goodstype);
			rtn=ajaxReturn(true, "更新成功");
		} catch (Exception e) {
			// TODO: handle exception
			rtn=ajaxReturn(false, "更新失败");
		}
		return rtn;
	}
	
	//根据id查询部门信息
	@ResponseBody
	@RequestMapping("goodstype_get")
	public Goodstype get(long uuid){
		Goodstype goodstype=goodstypeService.get(uuid);
		return goodstype;
	}
	
	//查询
	@ResponseBody
	@RequestMapping("goodstype_getList")
	public List<Goodstype> getList(Goodstype goodstype){
		List<Goodstype> ld=goodstypeService.findByCondition(goodstype);
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
