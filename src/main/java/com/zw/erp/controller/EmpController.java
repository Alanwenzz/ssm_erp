package com.zw.erp.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zw.erp.pojo.Emp;
import com.zw.erp.pojo.Role;
import com.zw.erp.pojo.Tree;
import com.zw.erp.service.EmpService;
import com.zw.erp.service.RoleService;

@Controller
public class EmpController {
	@Autowired
	private EmpService empService;
	@Autowired
	private RoleService roleService;
	
	//页面
	@RequestMapping("emp")
	public String emp() {
		return "emp";
	}
	
	//用户角色设置
	@RequestMapping("empRoleSet")
	public String empRoleSet() {
		return "empRoleSet";
	}

	//添加
	@ResponseBody
	@RequestMapping("emp_add")
	public Map<String, Object> add(Emp emp){
		Map<String, Object> rtn;
		try {
			empService.add(emp);
			rtn=ajaxReturn(true, "添加成功");
		} catch (Exception e) {
			// TODO: handle exception
			rtn=ajaxReturn(false, "添加失败");
		}
		return rtn;
	}
	//删除
	@ResponseBody
	@RequestMapping("emp_delete")
	public Map<String, Object> delete(long uuid){
		Map<String, Object> rtn;
		try {
			empService.delete(uuid);
			rtn=ajaxReturn(true, "删除成功");
		} catch (Exception e) {
			// TODO: handle exception
			rtn=ajaxReturn(false, "删除失败");
		}
		return rtn;
	}
	//修改
	@ResponseBody
	@RequestMapping("emp_update")
	public Map<String, Object> update(Emp emp){
		Map<String, Object> rtn;
		try {
			empService.update(emp);
			rtn=ajaxReturn(true, "更新成功");
		} catch (Exception e) {
			// TODO: handle exception
			rtn=ajaxReturn(false, "更新失败");
		}
		return rtn;
	}
	//根据id查询用户信息
	@ResponseBody
	@RequestMapping("emp_get")
	public Emp get(long uuid){
		Emp emp=empService.get(uuid);
		return emp;
	}
	//查询
	@ResponseBody
	@RequestMapping("emp_getList")
	public List<Emp> getList(Emp emp,@DateTimeFormat(pattern ="yyyy-MM-dd")Date birthday2){		
		List<Emp> lEmps=empService.findByCondition(emp, birthday2);
		return lEmps;
	}
	//查询用户对应的角色
	@ResponseBody
	@RequestMapping("emp_readEmpRoles")
	public List<Tree> readEmpRoles(long uuid){
		List<Role> allRoles=roleService.getList();
		List<Role> lRoles=empService.readEmpRoles(uuid);
		List<Tree> lTrees=new ArrayList<Tree>();
		for(Role r:allRoles) {
			Tree tree=new Tree();
			tree.setId(r.getUuid().toString());
			tree.setText(r.getName());
			if(lRoles.contains(r)) {
				tree.setChecked(true);
			}
			lTrees.add(tree);
		}
		return lTrees;
	}
	//更新员工角色
	@ResponseBody
	@RequestMapping("emp_updateEmpRoles")
	public int updateEmpRoles(long uuid,String checkedStr) {
		//更新员工角色
		try {
			empService.updateEmpRoles(uuid,checkedStr);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}
	
	//ajax返回
	public Map<String, Object> ajaxReturn(boolean success,String message){
		Map<String, Object> rtn = new HashMap<String, Object>();
		rtn.put("success",success);
		rtn.put("message",message);
		return rtn;
	}
	
}
