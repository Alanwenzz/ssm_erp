package com.zw.erp.controller;


import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zw.erp.pojo.Emp;
import com.zw.erp.service.EmpService;



@Controller
public class LoginController {
	@Autowired
	private EmpService empService;
	@Autowired
	private HttpSession httpSession;

	@RequestMapping("login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("index")
	public String index(){
		return "index";
	}
	
	@ResponseBody
	@RequestMapping("login_checkUser")
	public Map<String, Object> checkUser(Emp emp) {
		System.out.println(emp.getName()+" "+emp.getPassword());
		Map<String, Object> rtn;
		try{
			Emp loginUser = empService.findByUsernameAndPwd(emp);
			if(loginUser != null){
				httpSession.setAttribute("loginUser", loginUser);
				rtn=ajaxReturn(true, "");
			}else{
				rtn=ajaxReturn(false, "用户名或密码不正确");
			}
		}catch(Exception ex){
			ex.printStackTrace();
			rtn=ajaxReturn(false, "登陆失败");
		}
		return rtn;
	}
	
	
	//ajax返回
	public Map<String, Object> ajaxReturn(boolean success, String message){
		//
		Map<String, Object> rtn = new HashMap<String, Object>();
		rtn.put("success",success);
		rtn.put("message",message);
		return rtn;
	}
}
