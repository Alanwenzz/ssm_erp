package com.zw.erp.service;

import java.util.Date;
import java.util.List;

import com.zw.erp.pojo.Emp;
import com.zw.erp.pojo.Menu;
import com.zw.erp.pojo.Role;

public interface EmpService {
	public void add(Emp emp);
	public void delete(long uuid);
	public void update(Emp emp);
	public List<Emp> findByCondition(Emp emp,Date birthday2);
	public List<Emp> getAll();
	public Emp findByUsernameAndPwd(Emp emp);
	public Emp get(long uuid);
	public void updatePwd(long uuid, String txtOldPass, String txtNewPass);
	public List<Menu> getMenuByEmpuuid(long id);
	public Menu readMenusByEmpuuid(long id);
	public List<Role> readEmpRoles(long uuid);
	public void updateEmpRoles(long uuid, String checkedStr);
	public long getCount();
	public List<Emp> getListByPage(int page, int rows);
}

