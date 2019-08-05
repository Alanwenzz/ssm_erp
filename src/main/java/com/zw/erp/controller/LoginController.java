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
	
	//�û�������У��
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
				rtn=ajaxReturn(false, "�û��������벻��ȷ");
			}
		}catch(Exception ex){
			ex.printStackTrace();
			rtn=ajaxReturn(false, "��½ʧ��");
		}
		return rtn;
	}
	
	//��ʾ����
	@ResponseBody
	@RequestMapping("login_showName")
	public Map<String, Object> showName(){
		Map<String, Object> rtn;
		//��session��ȡֵ
		Emp emp = (Emp) httpSession.getAttribute("loginUser");
		//sessionֵ ��ʾ
		if(null != emp){
			rtn=ajaxReturn(true, emp.getName());
		}else{
			rtn=ajaxReturn(false, "");
		}
		return rtn;
	}
	
	//�޸�����
	@ResponseBody
	@RequestMapping("emp_updatePwd")
	public Map<String, Object> updatePwd(String oldPwd,String newPwd){
		Map<String, Object> rtn;
		Emp loginUser = (Emp) httpSession.getAttribute("loginUser");
		//session是否会超时，用户是否登陆过了
		if(null == loginUser){
			rtn=ajaxReturn(false, "�����µ�¼");
			return rtn;
		}
		try {
			empService.updatePwd(loginUser.getUuid(), oldPwd, newPwd);
			rtn=ajaxReturn(true, "�޸�����ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			rtn=ajaxReturn(false, "�޸�����ʧ��");
		}
		return rtn;
	}
	
	//�˳�����session
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

	
	//ajax����
	public Map<String, Object> ajaxReturn(boolean success, String message){
		//
		Map<String, Object> rtn = new HashMap<String, Object>();
		rtn.put("success",success);
		rtn.put("message",message);
		return rtn;
	}
}
