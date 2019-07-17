package com.zw.erp.service;

import java.util.Date;
import java.util.List;

import com.zw.erp.pojo.Emp;

public interface EmpService {
	public void add(Emp emp);
	public void delete(long uuid);
	public void update(Emp emp);
	public List<Emp> findByCondition(Emp emp,Date birthday2);
	public Emp findByUsernameAndPwd(Emp emp);
	public Emp get(long uuid);
}

