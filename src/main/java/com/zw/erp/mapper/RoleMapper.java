package com.zw.erp.mapper;

import java.util.List;

import com.zw.erp.pojo.Menu;
import com.zw.erp.pojo.Role;

public interface RoleMapper {
	public List<Role> getList();

	public List<Menu> readRoleMenus(long uuid);
}
