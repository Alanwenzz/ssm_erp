package com.zw.erp.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zw.erp.mapper.EmpMapper;
import com.zw.erp.pojo.Emp;
import com.zw.erp.service.EmpService;

@Service
public class EmpServiceImpl implements EmpService{
	@Autowired
	private EmpMapper empMapper;

	public void add(Emp emp) {
		// TODO Auto-generated method stub
		empMapper.insert(emp);
	}

	public void delete(long uuid) {
		// TODO Auto-generated method stub
		empMapper.delete(uuid);
	}

	public void update(Emp emp) {
		// TODO Auto-generated method stub
		empMapper.update(emp);;
	}

	public List<Emp> findByCondition(Emp emp, Date birthday2) {
		// TODO Auto-generated method stub
		return empMapper.findByCondition(emp, birthday2);
	}
	
	public Emp findByUsernameAndPwd(Emp emp) {
		return empMapper.findByUsernameAndPwd(emp);
	}

	public Emp get(long uuid) {
		// TODO Auto-generated method stub
		return empMapper.get(uuid);
	} 
	
}
