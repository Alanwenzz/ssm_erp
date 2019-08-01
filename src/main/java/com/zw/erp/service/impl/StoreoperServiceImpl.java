package com.zw.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zw.erp.mapper.StoreoperMapper;
import com.zw.erp.pojo.Storeoper;
import com.zw.erp.service.StoreoperService;

@Service
public class StoreoperServiceImpl implements StoreoperService{
	@Autowired
	private StoreoperMapper storeoperMapper;

	public void add(Storeoper storeoper) {
		// TODO Auto-generated method stub
		storeoperMapper.insert(storeoper);
	}

	public void delete(long uuid) {
		// TODO Auto-generated method stub
		storeoperMapper.delete(uuid);
	}

	public void update(Storeoper storeoper) {
		// TODO Auto-generated method stub
		storeoperMapper.update(storeoper);;
	}

	public List<Storeoper> findByCondition(Storeoper storeoper) {
		// TODO Auto-generated method stub
		return storeoperMapper.findByCondition(storeoper);
	}

	public Storeoper get(long uuid) {
		// TODO Auto-generated method stub
		return storeoperMapper.get(uuid);
	}
	
}
