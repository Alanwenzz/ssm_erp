package com.zw.erp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zw.erp.pojo.Emp;
import com.zw.erp.pojo.Menu;
import com.zw.erp.service.EmpService;
import com.zw.erp.service.MenuService;

@Controller
public class MenuController {
	@Autowired
	private MenuService menuService;
	@Autowired
	private EmpService empService;
	@Autowired
	private HttpSession httpSession;

	@ResponseBody
	@RequestMapping("menu_getMenuTree")
	public Menu getMenuTree(){
		//从0读取整个表
		Emp emp=(Emp) httpSession.getAttribute("loginUser");
		Menu menu = empService.readMenusByEmpuuid(3l);
		return menu;
	}
	
	@RequestMapping("menu_getMenuTree1")
	@ResponseBody
	public Menu getMenuTree1(){
		//从0读取整个表
		
		Menu menu=menuService.get("-1");
		return menu;
	}
	
	@RequestMapping("menu_getMenuTree2")
	@ResponseBody
	public List<Menu> getMenuTree2(){
		
		List<Menu> menu = empService.getMenuByEmpuuid(3l);
		return menu;
	}
	
}
