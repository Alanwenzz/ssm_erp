package com.zw.erp.service;

import java.util.List;

import com.zw.erp.pojo.Menu;
import com.zw.erp.pojo.Role;

public interface RoleService {
	public List<Role> getList();

	public List<Menu> readRoleMenus(long uuid);
	
	public void updateRoleMenus(long mid,String checkedStr);
}
