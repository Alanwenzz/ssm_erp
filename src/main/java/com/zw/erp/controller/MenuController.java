package com.zw.erp.controller;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zw.erp.pojo.Emp;
import com.zw.erp.pojo.Menu;
import com.zw.erp.service.EmpService;

@Controller
public class MenuController {
	@Autowired
	private EmpService empService;
	@Autowired
	private HttpSession httpSession;

	@ResponseBody
	@RequestMapping("menu_getMenuTree")
	public Menu getMenuTree(){
		//从0读取整个表
		Emp emp=(Emp) httpSession.getAttribute("loginUser");
		Menu menu = empService.readMenusByEmpuuid(emp.getUuid());
		return menu;
	}
	
}
