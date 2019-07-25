package com.zw.erp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zw.erp.pojo.Menu;
import com.zw.erp.pojo.Role;
import com.zw.erp.pojo.Tree;
import com.zw.erp.service.MenuService;
import com.zw.erp.service.RoleService;

@Controller
public class RoleController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;

	
	@RequestMapping("/roleMenuSetM")
	public String roleMenuSetM() {
		return "roleMenuSetM";
	}
	
	@RequestMapping("/roleMenuSet")
	public String roleMenuSet() {
		return "roleMenuSet";
	}
	
	@ResponseBody
	@RequestMapping("/role_getList")
	public List<Role> getList(){
		return roleService.getList();
	}
	
	@ResponseBody
	@RequestMapping("/role_updateRoleMenus")
	public int updateRoleMenus(long uuid,String checkedStr){
		//���½�ɫӵ�еĲ˵�
		try {
			roleService.updateRoleMenus(uuid,checkedStr);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	
	@ResponseBody
	@RequestMapping("/role_readRoleMenus")
	public List<Tree> readRoleMenus(long uuid){
		//���в˵�
		Menu menu=menuService.get("-1");
		//��ɫӵ�еĲ˵�
		List<Menu> lMenus=roleService.readRoleMenus(uuid);
		
		List<Tree> lTrees=new ArrayList<Tree>();
		//һ���˵�
		for(Menu m1: menu.getMenus()) {
			Tree tree1=new Tree();
			tree1.setText(m1.getMenuname());
			tree1.setId(m1.getMenuid());
			//�����˵�
			for(Menu m2:m1.getMenus()) {
				Tree tree2=new Tree();
				tree2.setId(m2.getMenuid());
				tree2.setText(m2.getMenuname());
				//�û�������ǰ�˵���ѡ��
				if(lMenus.contains(m2)) {
					tree2.setChecked(true);
				}
				tree1.getChildren().add(tree2);
			}
			lTrees.add(tree1);
		}
		
		return lTrees;
	}
}
