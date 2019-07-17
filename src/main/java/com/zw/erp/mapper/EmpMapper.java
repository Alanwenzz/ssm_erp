package com.zw.erp.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zw.erp.pojo.Emp;

public interface EmpMapper {
	public List<Emp> findByCondition(@Param("emp")Emp emp, @Param("birthday2")Date birthday2);
	public void insert(Emp emp);
	public void delete(long uuid);
	public void update(Emp emp);
	public Emp get(long uuid);
	public Emp findByUsernameAndPwd(Emp emp);
	public void updatePwd(@Param("uuid")long uuid,@Param("txtNewPass")String txtNewPass);
}
