package com.zw.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zw.erp.mapper.MenuMapper;
import com.zw.erp.mapper.RoleMapper;
import com.zw.erp.pojo.Menu;
import com.zw.erp.pojo.Role;
import com.zw.erp.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private MenuMapper menuMapper;

	public List<Role> getList() {
		// TODO Auto-generated method stub
		return roleMapper.getList();
	}

	public List<Menu> readRoleMenus(long uuid) {
		// TODO Auto-generated method stub
		return roleMapper.readRoleMenus(uuid);
	}

	public void updateRoleMenus(long uuid,String checkedStr) {
		// TODO Auto-generated method stub
		//��յ�ǰ�û����в˵�
		menuMapper.deleteAllUmenu(uuid);
		//��id�ָ����
		String[] ids = checkedStr.split(",");
		//ѭ������
		for(String id:ids) {
			menuMapper.insertUmenu(uuid, id);
		}
	}
	
}
