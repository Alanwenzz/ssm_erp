package com.zw.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zw.erp.mapper.DepMapper;
import com.zw.erp.pojo.Dep;
import com.zw.erp.service.DepService;

@Service
public class DepServiceImpl implements DepService{
	@Autowired
	private DepMapper depMapper;

	public void add(Dep dep) {
		// TODO Auto-generated method stub
		depMapper.insert(dep);
	}

	public void delete(long uuid) {
		// TODO Auto-generated method stub
		depMapper.delete(uuid);
	}

	public void update(Dep dep) {
		// TODO Auto-generated method stub
		depMapper.update(dep);;
	}

	public List<Dep> findByCondition(Dep dep) {
		// TODO Auto-generated method stub
		return depMapper.findByCondition(dep);
	}

	public Dep get(long uuid) {
		// TODO Auto-generated method stub
		return depMapper.get(uuid);
	}
	
}
