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
	
	//用户名密码校验
	@ResponseBody
	@RequestMapping("login_checkUser")
	public Map<String, Object> checkUser(Emp emp) {
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
	
	//显示名字
	@ResponseBody
	@RequestMapping("login_showName")
	public Map<String, Object> showName(){
		Map<String, Object> rtn;
		//从session中取值
		Emp emp = (Emp) httpSession.getAttribute("loginUser");
		//session值 显示
		if(null != emp){
			rtn=ajaxReturn(true, emp.getName());
		}else{
			rtn=ajaxReturn(false, "");
		}
		return rtn;
	}
	
	//修改密码
	@ResponseBody
	@RequestMapping("emp_updatePwd")
	public Map<String, Object> updatePwd(String oldPwd,String newPwd){
		Map<String, Object> rtn;
		Emp loginUser = (Emp) httpSession.getAttribute("loginUser");
		//session鏄惁浼氳秴鏃讹紝鐢ㄦ埛鏄惁鐧婚檰杩囦簡
		if(null == loginUser){
			rtn=ajaxReturn(false, "请重新登录");
			return rtn;
		}
		try {
			empService.updatePwd(loginUser.getUuid(), oldPwd, newPwd);
			rtn=ajaxReturn(true, "修改密码成功");
		} catch (Exception e) {
			e.printStackTrace();
			rtn=ajaxReturn(false, "修改密码失败");
		}
		return rtn;
	}
	
	//退出销毁session
	@RequestMapping("login_loginOut")
	@ResponseBody
	public int loginOut(){
		try {
			httpSession.invalidate();
			return 0;
		} catch (Exception e) {
			// TODO: handle exception
		}		
		return 1;
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
