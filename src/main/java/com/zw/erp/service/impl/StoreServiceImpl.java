package com.zw.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zw.erp.mapper.StoreMapper;
import com.zw.erp.pojo.Store;
import com.zw.erp.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService{
	@Autowired
	private StoreMapper storeMapper;

	public void add(Store store) {
		// TODO Auto-generated method stub
		storeMapper.insert(store);
	}

	public void delete(long uuid) {
		// TODO Auto-generated method stub
		storeMapper.delete(uuid);
	}

	public void update(Store store) {
		// TODO Auto-generated method stub
		storeMapper.update(store);;
	}

	public List<Store> findByCondition(Store store) {
		// TODO Auto-generated method stub
		return storeMapper.findByCondition(store);
	}

	public Store get(long uuid) {
		// TODO Auto-generated method stub
		return storeMapper.get(uuid);
	}
	
}
