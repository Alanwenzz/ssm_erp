package com.zw.erp.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zw.erp.pojo.Goods;
import com.zw.erp.service.GoodsService;

@Controller
public class GoodsController {
	@Autowired
	private GoodsService goodsService;
	
	//页面
	@RequestMapping("goods")
	public String goods() {
		return "goods";
	}
	
	//页面
	@RequestMapping("goodsM")
	public String goodsBS() {
		return "goodsM";
	}
	
	//添加
	@ResponseBody
	@RequestMapping("goods_add")
	public Map<String, Object> add(Goods goods){
		Map<String, Object> rtn;
		try {
			goodsService.add(goods);
			rtn=ajaxReturn(true, "添加成功");
		} catch (Exception e) {
			// TODO: handle exception
			rtn=ajaxReturn(true, "添加失败");
		}
		return rtn;
	}
	//删除
	@ResponseBody
	@RequestMapping("goods_delete")
	public Map<String, Object> delete(long uuid){
		Map<String, Object> rtn;
		try {
			goodsService.delete(uuid);
			rtn=ajaxReturn(true, "删除成功");
		} catch (Exception e) {
			// TODO: handle exception
			rtn=ajaxReturn(true, "删除失败");
		}
		return rtn;
	}
	//修改
	@ResponseBody
	@RequestMapping("goods_update")
	public Map<String, Object> update(Goods goods){
		Map<String, Object> rtn;
		try {
			goodsService.update(goods);
			rtn=ajaxReturn(true, "更新成功");
		} catch (Exception e) {
			// TODO: handle exception
			rtn=ajaxReturn(false, "更新失败");
		}
		return rtn;
	}
	
	//根据id查询部门信息
	@ResponseBody
	@RequestMapping("goods_get")
	public Goods get(long uuid){
		Goods goods=goodsService.get(uuid);
		return goods;
	}
	
//	//查询
//	@ResponseBody
//	@RequestMapping("goods_getList")
//	public List<Goods> getList(Goods goods){
//		List<Goods> ld=goodsService.findByCondition(goods);
//		return ld;
//	}
	
	//查询
	@ResponseBody
	@RequestMapping("goods_getList")
	public List<Goods> getList(){
		List<Goods> ld=goodsService.getList();
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
