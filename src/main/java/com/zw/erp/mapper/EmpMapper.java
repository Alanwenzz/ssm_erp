package com.zw.erp.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zw.erp.pojo.Emp;
import com.zw.erp.pojo.Menu;
import com.zw.erp.pojo.Role;

public interface EmpMapper {
	public List<Emp> findByCondition(@Param("emp")Emp emp, @Param("birthday2")Date birthday2);
	public List<Emp> getAll();
	public void insert(Emp emp);
	public void delete(long uuid);
	public void update(Emp emp);
	public Emp get(long uuid);
	public Emp findByUsernameAndPwd(Emp emp);
	public void updatePwd(@Param("uuid")long uuid,@Param("txtNewPass")String txtNewPass);
	public List<Menu> getMenuByEmpuuid(Long id);
	public List<Role> readEmpRoles(long uuid);
	public void deleteAllErole(long uuid);
	public void insertErole(@Param("eid")long uuid,@Param("rid")long id);
}
