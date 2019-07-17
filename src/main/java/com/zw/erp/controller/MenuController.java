package com.zw.erp.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zw.erp.pojo.Menu;
import com.zw.erp.service.MenuService;

@Controller
public class MenuController {
	@Autowired
	private MenuService menuService;

	@ResponseBody
	@RequestMapping("menu_getMenuTree")
	public List<Menu> getMenuTree(){
		//从0读取整个表
		List<Menu> lm = menuService.get("0");
		return lm;
	}
	
}
